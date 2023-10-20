package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.BasketProductDto;
import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.entity.Product;
import lombok.Data;

@Data
public class AddBasketProductRequest {
    private Integer quantity;
    private Integer productId;

    public BasketProductDto toDto() {
        return BasketProductDto.builder()
                .quantity(quantity)
                .product(ProductDto.builder().id(productId).build())
                .build();
    }
}
