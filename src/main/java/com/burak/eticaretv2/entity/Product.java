package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class Product extends AbstractEntity {
    private String name;
    private Double unitPrice;
    private Integer unitsInStock;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    @JsonBackReference
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    public ProductDto toDto(){
        return ProductDto.builder()
                .id(getId())
                .name(getName())
                .unitPrice(getUnitPrice())
                .unitsInStock(getUnitsInStock())
                .shop(getShop())
                .category(getCategory())
                .build();
    }
}
