package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.BasketDto;
import com.burak.eticaretv2.Dto.OrderDto;
import com.burak.eticaretv2.Dto.OrderProductDto;
import com.burak.eticaretv2.Service.AddressService;
import com.burak.eticaretv2.Service.BasketService;
import com.burak.eticaretv2.Service.OrderService;
import com.burak.eticaretv2.Service.ProductService;
import com.burak.eticaretv2.entity.*;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderProductServiceImpl orderProductServiceImpl;
    @Autowired
    private BasketService basketService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ProductService productService;


    @Override
    public List<OrderDto> findAll() {
        return OrderDto.toDtoList(repository.findAll());
    }

    @Override
    public OrderDto findById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Sipariş Bulunamadı :" + id);
        }
        return repository.findById(id).get().toDto();
    }

    @Override
    public List<OrderDto> findOrdersByCustomerId(Integer customerId) {

        return null;
    }

    @Override
    public OrderDto createOrder(Integer customerId, Integer addressId) {
        Basket basket = basketService.findBasketByCustomer_IdAndStatusCode(customerId, Basket.ALISVERIS_SEPETI);
        Order order = Order.builder()
                .orderProductList(new ArrayList<>())
                .orderDate(new Date())
                .deliveryLocation(createAddressText(addressService.findById(addressId).toEntity()))
                .totalAmount(0.0)
                .build();

        for (BasketProduct basketProduct : basket.getBasketProductList()) {
            Product product = basketProduct.getProduct();
            product.setUnitsInStock(basketProduct.getProduct().getUnitsInStock() - basketProduct.getQuantity());
            order.getOrderProductList().add(
                    OrderProduct.builder()
                            .statusCode(OrderProduct.SIPARIS_ALINDI)
                            .basketProduct(basketProduct)
                            .order(order)
                            .build()
            );
            productService.save(product);
            order.setTotalAmount(order.getTotalAmount() + basket.getTotalAmount());
        }

        basket.setStatusCode(Basket.SIPARIS_SEPETI);
        basketService.save(basket);
        return repository.save(order).toDto();
    }

    private String createAddressText(Address address) {
        return address.getAddressText() + " " + address.getPostalCode() + "   " + address.getCity() + "/" + address.getDistrict();
    }

    @Override
    public OrderDto updateOrderProduct(Integer orderProductId, Integer statusCode) {
        OrderProduct orderProduct = orderProductServiceImpl.findById(orderProductId);
        orderProduct.setStatusCode(statusCode);
        orderProductServiceImpl.save(orderProduct);

        return repository.findById(orderProduct.getOrder().getId()).get().toDto();
    }

    @Override
    public Void deleteOrder(Integer id) {
        Order order = repository.findById(id).get();
        BasketProduct basketProduct = order.getOrderProductList().get(0).getBasketProduct();
        repository.deleteById(id);
        basketService.delete(basketProduct.getBasket().getId());
        return null;
    }
}
