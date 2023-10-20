package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Order;
import com.burak.eticaretv2.entity.OrderProduct;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class OrderDto {
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    private List<OrderProduct> orderProductList;
    private String deliveryLocation;
    private Double totalAmount;

    public Order toEntity(){
        return Order.builder()
                .orderDate(orderDate)
                .deliveryLocation(deliveryLocation)
                .orderProductList(orderProductList)
                .totalAmount(totalAmount)
                .build();
    }

    public static List<OrderDto> toDtoList(List<Order> orderList){
        return orderList
                .stream()
                .map(Order::toDto)
                .collect(Collectors.toList());
    }
}
