package com.burak.eticaretv2.repository;

import com.burak.eticaretv2.entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketProductRepository extends JpaRepository<BasketProduct, Integer> {
    List<BasketProduct> findBasketProductsByBasket_Id(Integer basketId);

    BasketProduct findBasketProductByProduct_Id(Integer productId);

    BasketProduct findBasketProductByBasket_IdAndProduct_Id(Integer basketId, Integer productId);
}
