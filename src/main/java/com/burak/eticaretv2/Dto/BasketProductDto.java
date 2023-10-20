package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Basket;
import com.burak.eticaretv2.entity.BasketProduct;
import com.burak.eticaretv2.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class BasketProductDto {
    private Integer id;
    private Integer quantity;
    private Double basketProductTotalAmount;
    private ProductDto product;
    @JsonIgnore
    private Basket basket;

    public BasketProduct toEntity(){
        return BasketProduct.builder()
                .quantity(quantity)
                .basketProductTotalAmount(basketProductTotalAmount)
                .product(Product.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .unitPrice(product.getUnitPrice())
                        .unitsInStock(product.getUnitsInStock())
                        .shop(product.getShop())
                        .category(product.getCategory())
                        .build())
                .basket(basket)
                .build();
    }

    public static List<BasketProductDto> toDtoList(List<BasketProduct> basketProductList){
        return basketProductList
                .stream()
                .map(BasketProduct::toDto)
                .collect(Collectors.toList());
    }


}
