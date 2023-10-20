package com.burak.eticaretv2.repository;

import com.burak.eticaretv2.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Integer> {
    List<Basket> findBasketByStatusCode(Integer statusCode);
    List<Basket> findBasketByCustomer_Id(Integer customerId);
    Optional<Basket> findBasketByCustomer_IdAndStatusCode(Integer customerId, Integer statusCode);
}
