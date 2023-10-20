package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.BasketProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class BasketProduct extends AbstractEntity {

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    @JsonBackReference
    private Basket basket;

    private Integer quantity;
    private Double basketProductTotalAmount;

    public BasketProductDto toDto(){
        return BasketProductDto.builder()
                .id(getId())
                .product(getProduct().toDto())
                .basket(getBasket())
                .quantity(getQuantity())
                .basketProductTotalAmount(getBasketProductTotalAmount())
                .build();
    }

}
