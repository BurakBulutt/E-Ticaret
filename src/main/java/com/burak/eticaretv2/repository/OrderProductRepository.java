package com.burak.eticaretv2.repository;

import com.burak.eticaretv2.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
