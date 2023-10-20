package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Shop;
import lombok.Data;

@Data
public class UpdateProductRequest {
    private String name;
    private Double unitPrice;
    private Integer unitsInStock;

    public ProductDto toDto(){
        return ProductDto.builder()
                .name(name)
                .unitPrice(unitPrice)
                .unitsInStock(unitsInStock)
                .build();
    }
}
