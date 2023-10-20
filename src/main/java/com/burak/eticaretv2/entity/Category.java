package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.Dto.ProductDto;
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
public class Category extends AbstractEntity {
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> productList;

    public CategoryDto toDto(){
        return CategoryDto.builder()
                .id(getId())
                .name(getName())
                .productList(getProductList())
                .build();
    }
}
