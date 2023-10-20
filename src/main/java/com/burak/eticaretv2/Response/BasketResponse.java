package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.BasketDto;
import com.burak.eticaretv2.Dto.BasketProductDto;
import com.burak.eticaretv2.Dto.CustomerDto;
import com.burak.eticaretv2.entity.BasketProduct;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class BasketResponse {
    private Integer id;
    private Integer statusCode;
    private Double totalAmount;
    private CustomerDto customer;
    private List<BasketProductDto> basketProductList;

    public static BasketResponse toResponse(BasketDto dto){
        return BasketResponse.builder()
                .id(dto.getId())
                .statusCode(dto.getStatusCode())
                .totalAmount(dto.getTotalAmount())
                .customer(dto.getCustomer())
                .basketProductList(dto.getBasketProductList().stream().map(BasketProduct::toDto).collect(Collectors.toList()))
                .build();
    }
}
