package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CategoryDto {
    private Integer id;
    private String name;
    @JsonIgnore
    private List<Product> productList;

    public Category toEntity() {
        return Category.builder()
                .id(id)
                .name(name)
                .productList(productList)
                .build();
    }

    public static List<CategoryDto> toDtoList(List<Category> categoryList){
        return categoryList
                .stream()
                .map(category -> category.toDto())
                .collect(Collectors.toList());
    }


}
