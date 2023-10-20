package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.OrderDto;
import com.burak.eticaretv2.Dto.OrderProductDto;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private List<OrderProductDto> orderProductList;
    private String deliveryLocation;
    private Double totalAmount;

    public static OrderResponse toResponse(OrderDto dto){
        return OrderResponse.builder()
                .id(dto.getId())
                .orderDate(dto.getOrderDate())
                .orderProductList(OrderProductDto.toDtoList(dto.getOrderProductList()))
                .deliveryLocation(dto.getDeliveryLocation())
                .totalAmount(dto.getTotalAmount())
                .build();
    }
}
