package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.entity.BasketProduct;

import java.util.List;

public interface BasketProductService {
    List<BasketProduct> findAll();

    BasketProduct findById(Integer id);

    BasketProduct findBasketProductByBasket_IdAndProduct_Id(Integer basketId, Integer productId);

    BasketProduct save(BasketProduct basketProduct);

    Void delete(Integer id);
}
