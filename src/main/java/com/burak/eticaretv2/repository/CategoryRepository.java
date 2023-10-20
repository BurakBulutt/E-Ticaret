package com.burak.eticaretv2.repository;

import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
