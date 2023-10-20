package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Basket;
import com.burak.eticaretv2.entity.BasketProduct;
import com.burak.eticaretv2.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class BasketDto {
    private Integer id;
    private Integer statusCode;
    private Double totalAmount;
    private CustomerDto customer;
    private List<BasketProduct> basketProductList;

    public Basket toEntity(){
        return Basket.builder()
                .id(id)
                .statusCode(statusCode)
                .totalAmount(totalAmount)
                .customer(Customer.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .birthDate(customer.getBirthDate())
                        .email(customer.getEmail())
                        .password(customer.getPassword())
                        .basketList(customer.getBasketList())
                        .addressList(customer.getAddressList())
                        .build())
                .basketProductList(basketProductList)
                .build();
    }

    public static List<BasketDto> toDtoList(List<Basket> basketList){
        return basketList
                .stream()
                .map(basket -> basket.toDto())
                .collect(Collectors.toList());
    }
}
