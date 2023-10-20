package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Service.BasketProductService;
import com.burak.eticaretv2.entity.BasketProduct;
import com.burak.eticaretv2.repository.BasketProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {
    @Autowired
    private BasketProductRepository repository;

    @Override
    public List<BasketProduct> findAll() {
        return repository.findAll();
    }

    @Override
    public BasketProduct findBasketProductByBasket_IdAndProduct_Id(Integer basketId, Integer productId) {
        return repository.findBasketProductByBasket_IdAndProduct_Id(basketId, productId);
    }

    @Override
    public BasketProduct findById(Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public BasketProduct save(BasketProduct basketProduct) {
        return repository.save(basketProduct);
    }

    @Override
    public Void delete(Integer id) {
        repository.deleteById(id);
        return null;
    }
}
