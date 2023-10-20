package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Product;
import com.burak.eticaretv2.entity.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private Double unitPrice;
    private Integer unitsInStock;
    @JsonIgnore
    private Shop shop;
    @JsonIgnore
    private Category category;


    public static List<ProductDto> toDtoList(List<Product> productList){
        return productList
                .stream()
                .map(product -> product.toDto())
                .collect(Collectors.toList());
    }

    public Product toEntity(){
        return Product.builder()
                .name(name)
                .unitPrice(unitPrice)
                .unitsInStock(unitsInStock)
                .shop(shop)
                .category(category)
                .build();
    }

}
