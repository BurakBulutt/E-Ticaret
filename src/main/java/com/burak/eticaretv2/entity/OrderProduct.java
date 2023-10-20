package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.BasketProductDto;
import com.burak.eticaretv2.Dto.OrderProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct extends AbstractEntity {
    public static Integer SIPARIS_ALINDI = 244;
    public static Integer HAZIRLANIYOR = 254;
    public static Integer KARGODA = 264;
    public static Integer TESLIM_EDILDI = 274;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    private Order order;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private BasketProduct basketProduct;

    private Integer statusCode;

    public OrderProductDto toDto(){
        return OrderProductDto.builder()
                .id(getId())
                .basketProduct(BasketProductDto.builder()
                        .id(basketProduct.getId())
                        .product(basketProduct.getProduct().toDto())
                        .quantity(basketProduct.getQuantity())
                        .basket(basketProduct.getBasket())
                        .basketProductTotalAmount(basketProduct.getBasketProductTotalAmount())
                        .build())
                .statusCode(getStatusCode())
                .order(getOrder())
                .build();
    }
}
