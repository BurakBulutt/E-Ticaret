package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.OrderDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity(name = "customer_order")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractEntity {

    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderProduct> orderProductList;

    @Lob
    private String deliveryLocation;
    private Double totalAmount;

    public OrderDto toDto(){
        return OrderDto.builder()
                .id(getId())
                .orderDate(getOrderDate())
                .orderProductList(getOrderProductList())
                .deliveryLocation(getDeliveryLocation())
                .totalAmount(getTotalAmount())
                .build();
    }


}
