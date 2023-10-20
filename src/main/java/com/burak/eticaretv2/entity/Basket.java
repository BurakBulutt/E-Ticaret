package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.BasketDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@Entity
@NoArgsConstructor
public class Basket extends AbstractEntity {

    public static final Integer ALISVERIS_SEPETI = 266;
    public static final Integer SIPARIS_SEPETI = 306;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BasketProduct> basketProductList;

    private Integer statusCode;
    private Double totalAmount;

    public BasketDto toDto(){
        return BasketDto.builder()
                .id(getId())
                .customer(getCustomer().toDto())
                .basketProductList(getBasketProductList())
                .statusCode(getStatusCode())
                .totalAmount(getTotalAmount())
                .build();
    }
}
