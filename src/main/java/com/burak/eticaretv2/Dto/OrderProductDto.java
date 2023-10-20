package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.BasketProduct;
import com.burak.eticaretv2.entity.Order;
import com.burak.eticaretv2.entity.OrderProduct;
import com.burak.eticaretv2.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderProductDto {
    private Integer id;
    @JsonIgnore
    private Order order;
    private BasketProductDto basketProduct;
    private Integer statusCode;

    public OrderProduct toEntity() {
        return OrderProduct.builder()
                .order(order)
                .basketProduct(BasketProduct.builder()
                        .id(basketProduct.getId())
                        .product(Product.builder()
                                .id(basketProduct.getProduct().getId())
                                .category(basketProduct.getProduct().getCategory())
                                .shop(basketProduct.getProduct().getShop())
                                .unitPrice(basketProduct.getProduct().getUnitPrice())
                                .unitsInStock(basketProduct.getProduct().getUnitsInStock())
                                .name(basketProduct.getProduct().getName())
                                .build())
                        .basket(basketProduct.getBasket())
                        .basketProductTotalAmount(basketProduct.getBasketProductTotalAmount())
                        .quantity(basketProduct.getQuantity())
                        .build())
                .statusCode(statusCode)
                .build();
    }

    public static List<OrderProductDto> toDtoList(List<OrderProduct> orderProductList){
        return orderProductList
                .stream()
                .map(OrderProduct::toDto)
                .collect(Collectors.toList());
    }


}
