package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();

    List<ProductDto> findProductsByCategoryId(Integer categoryId);

    List<ProductDto> findProductsByShopId(Integer shopId);

    ProductDto findById(Integer id);

    ProductDto save(ProductDto dto);

    ProductDto update(Integer id, ProductDto dto);

    Void delete(Integer id);

    Product getById(Integer id);
    Product save(Product product);
}
