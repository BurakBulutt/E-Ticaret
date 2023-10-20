package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.BasketDto;
import com.burak.eticaretv2.Dto.BasketProductDto;
import com.burak.eticaretv2.Service.BasketService;
import com.burak.eticaretv2.Service.CustomerService;
import com.burak.eticaretv2.Service.ProductService;
import com.burak.eticaretv2.entity.Basket;
import com.burak.eticaretv2.entity.BasketProduct;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketProductServiceImpl serviceImpl;
    @Autowired
    private BasketRepository repository;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;

    @Override
    public List<BasketDto> findAll() {
        return BasketDto.toDtoList(repository.findAll());
    }

    @Override
    public List<BasketDto> findBasketsByCustomerId(Integer customerId) {
        if (customerService.getById(customerId) == null) {
            throw new NotFoundException("Seçili Id'ye Ait Müşteri yok : " + customerId);
        }
        if (repository.findBasketByCustomer_Id(customerId) == null) {
            throw new NotFoundException("Müşteriye Ait Sepet Bulunamadı : " + customerService.getById(customerId).getName());
        }
        return BasketDto.toDtoList(repository.findBasketByCustomer_Id(customerId));
    }

    @Override //SORGU ÇALIŞMIYOR
    public List<BasketDto> findBasketByStatusCode(Integer statusCode) {
        if (repository.findBasketByStatusCode(statusCode) == null) {
            throw new NotFoundException("Sepet Bulunamadı !");
        }
        return BasketDto.toDtoList(repository.findBasketByStatusCode(statusCode));
    }

    @Override
    public BasketDto findById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Sepet Bulunamadı ! ");
        }
        return repository.findById(id).get().toDto();
    }

    @Override
    public BasketDto addBasketProductOnBasket(Integer customerId, BasketProductDto basketProductDto) {
        if (repository.findBasketByCustomer_IdAndStatusCode(customerId, Basket.ALISVERIS_SEPETI).isEmpty()) {
            return basketNotFound(customerId, basketProductDto);
        }

        if (serviceImpl.findBasketProductByBasket_IdAndProduct_Id(repository.findBasketByCustomer_IdAndStatusCode(customerId,Basket.ALISVERIS_SEPETI).get().getId(),basketProductDto.getProduct().getId()) == null) {
            return productNotFound(repository.findBasketByCustomer_IdAndStatusCode(customerId, Basket.ALISVERIS_SEPETI).get(), basketProductDto);
        }

        return productFound(repository.findBasketByCustomer_IdAndStatusCode(customerId, Basket.ALISVERIS_SEPETI).get(), basketProductDto);
    }

    private BasketDto productFound(Basket basket, BasketProductDto basketProductDto) {
        BasketProduct basketProduct = serviceImpl.findBasketProductByBasket_IdAndProduct_Id(basket.getId(), basketProductDto.getProduct().getId());
        basketProduct.setQuantity(basketProduct.getQuantity() + basketProductDto.getQuantity());
        basketProduct.setBasketProductTotalAmount(basketProduct.getProduct().getUnitPrice() * basketProduct.getQuantity());
        basket.setTotalAmount(calculateBasket(basket));
        return repository.save(basket).toDto();
    }

    private BasketDto productNotFound(Basket basket, BasketProductDto basketProductDto) {
        basketProductDto.setBasket(basket);
        basketProductDto.setProduct(productService.getById(basketProductDto.getProduct().getId()).toDto());
        basketProductDto.setBasketProductTotalAmount(basketProductDto.getProduct().getUnitPrice() * basketProductDto.getQuantity());
        BasketProduct basketProduct = basketProductDto.toEntity();
        basket.getBasketProductList().add(basketProduct);
        basket.setTotalAmount(calculateBasket(basket));
        return repository.save(basket).toDto();
    }

    private BasketDto basketNotFound(Integer customerId, BasketProductDto basketProductDto) {
        Basket basket = Basket.builder()
                .totalAmount(0.0)
                .statusCode(Basket.ALISVERIS_SEPETI)
                .basketProductList(new ArrayList<>())
                .customer(customerService.getById(customerId))
                .build();
        basketProductDto.setBasket(basket);
        basketProductDto.setProduct(productService.getById(basketProductDto.getProduct().getId()).toDto());
        basketProductDto.setBasketProductTotalAmount(basketProductDto.getProduct().getUnitPrice() * basketProductDto.getQuantity());
        basket.getBasketProductList().add(basketProductDto.toEntity());
        basket.setTotalAmount(calculateBasket(basket));
        return repository.save(basket).toDto();
    }


    private Double calculateBasket(Basket basket) {
        basket.setTotalAmount(0.0);
        for (BasketProduct basketProduct : basket.getBasketProductList()) {
            basket.setTotalAmount(basket.getTotalAmount() + basketProduct.getBasketProductTotalAmount());
        }
        return basket.getTotalAmount();
    }

    @Override
    public BasketDto increaseBasketProductQuantity(Integer basketProductId) {
        BasketProduct basketProduct = serviceImpl.findById(basketProductId);
        basketProduct.setQuantity(basketProduct.getQuantity() + 1);
        basketProduct.setBasketProductTotalAmount(basketProduct.getBasketProductTotalAmount() + basketProduct.getProduct().getUnitPrice());
        Basket basket = repository.findById(basketProduct.getBasket().getId()).get();
        basket.setTotalAmount(calculateBasket(basket));
        return repository.save(basket).toDto();
    }

    @Override
    public BasketDto decreaseBasketProductQuantity(Integer basketProductId) {
        BasketProduct basketProduct = serviceImpl.findById(basketProductId);
        basketProduct.setQuantity(basketProduct.getQuantity() - 1);
        if(basketProduct.getQuantity() == 0){
            deleteBasketProductOnBasket(basketProductId);
        }
        basketProduct.setBasketProductTotalAmount(basketProduct.getBasketProductTotalAmount() - basketProduct.getProduct().getUnitPrice());
        Basket basket = repository.findById(basketProduct.getBasket().getId()).get();
        basket.setTotalAmount(calculateBasket(basket));
        return repository.save(basket).toDto();
    }

    @Override
    public Void deleteBasketProductOnBasket(Integer id) {
        Basket basket = repository.findById(serviceImpl.findById(id).getBasket().getId()).get();
        BasketProduct basketProduct = serviceImpl.findById(id);
        if (basket.getStatusCode().equals(Basket.SIPARIS_SEPETI)){
            throw new RuntimeException("SIPARIS SEPETINDEN URUN SILINEMEZ ! ");
        }
        serviceImpl.delete(id);
        if(checkBasketIsEmpty(basket)){
            repository.delete(basket);
        }else{
            basket.setTotalAmount(basket.getTotalAmount() - basketProduct.getBasketProductTotalAmount());
            repository.save(basket);
        }
        return null;
    }

    private boolean checkBasketIsEmpty(Basket basket){
        if (basket.getBasketProductList().isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public Basket findBasketByCustomer_IdAndStatusCode(Integer customerId,Integer statusCode) {
        return repository.findBasketByCustomer_IdAndStatusCode(customerId,statusCode).get();
    }

    @Override
    public Basket save(Basket basket) {
        return repository.save(basket);
    }

    @Override
    public Void delete(Integer id){
        repository.deleteById(id);
        return null;
    }
}
