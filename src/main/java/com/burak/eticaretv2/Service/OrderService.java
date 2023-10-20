package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.OrderDto;
import com.burak.eticaretv2.Dto.OrderProductDto;

import java.util.List;

public interface OrderService  {
    List<OrderDto> findAll();
    OrderDto findById(Integer id);
    List<OrderDto> findOrdersByCustomerId(Integer customerId);
    OrderDto createOrder(Integer customerId,Integer addressId);
    OrderDto updateOrderProduct(Integer orderProductId, Integer statusCode);
    Void deleteOrder(Integer id);
}
