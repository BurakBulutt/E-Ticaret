package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Shop;
import lombok.Data;

@Data
public class AddProductRequest {
    private String name;
    private Double unitPrice;
    private Integer unitsInStock;
    private Integer shopId;
    private Integer categoryId;

    public ProductDto toDto(){
        return ProductDto.builder()
                .name(name)
                .unitPrice(unitPrice)
                .unitsInStock(unitsInStock)
                .shop(Shop.builder().id(shopId).build())
                .category(Category.builder().id(categoryId).build())
                .build();
    }
}
