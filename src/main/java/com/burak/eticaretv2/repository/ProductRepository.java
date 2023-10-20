package com.burak.eticaretv2.repository;

import com.burak.eticaretv2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findProductsByCategory_Id(Integer categoryId);
    List<Product> findProductsByShop_Id(Integer shopId);
    Optional<Product> findProductByCategory_IdAndShop_IdAndName(Integer categoryId, Integer shopId, String name);

}
