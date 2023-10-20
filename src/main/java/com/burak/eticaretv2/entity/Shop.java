package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.Dto.ShopDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class Shop extends AbstractEntity{
    private String name;
    private String telNo;

    @OneToMany(mappedBy = "shop",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> productList;

    public ShopDto toDto(){
        return ShopDto.builder()
                .id(getId())
                .name(getName())
                .telNo(getTelNo())
                .productList(getProductList())
                .build();
    }

}
