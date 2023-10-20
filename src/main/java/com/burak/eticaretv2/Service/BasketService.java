package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.BasketDto;
import com.burak.eticaretv2.Dto.BasketProductDto;
import com.burak.eticaretv2.entity.Basket;

import java.util.List;

public interface BasketService {
    List<BasketDto> findAll();

    List<BasketDto> findBasketsByCustomerId(Integer customerId);

    List<BasketDto> findBasketByStatusCode(Integer statusCode);

    BasketDto findById(Integer id);

    BasketDto addBasketProductOnBasket(Integer customerId,BasketProductDto basketProductDto);

    BasketDto increaseBasketProductQuantity(Integer basketProductId);

    BasketDto decreaseBasketProductQuantity(Integer basketProductId);

    Void deleteBasketProductOnBasket(Integer id);

    Basket findBasketByCustomer_IdAndStatusCode(Integer customerId,Integer statusCode);
    Basket save(Basket basket);
    Void delete(Integer id);


}
