package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Customer;
import com.burak.eticaretv2.entity.Product;
import com.burak.eticaretv2.entity.Shop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ShopDto {
    private Integer id;
    private String name;
    private String telNo;
    @JsonIgnore
    private List<Product> productList;

    public Shop toEntity(){
        return Shop.builder()
                .name(name)
                .telNo(telNo)
                .productList(productList)
                .build();
    }

    public static List<ShopDto> toDtoList(List<Shop> shopList){
        return shopList
                .stream()
                .map(shop -> shop.toDto())
                .collect(Collectors.toList());
    }
}
