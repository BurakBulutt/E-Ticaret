package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Response.OrderResponse;
import com.burak.eticaretv2.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping
    private List<OrderResponse> getOrders(@RequestParam(required = false) String id,
                                          @RequestParam(required = false) String customerId) {
        if (id != null) {
            return Collections.singletonList(OrderResponse.toResponse(service.findById(Integer.parseInt(id))));
        } else if (customerId != null) {
            return service.findOrdersByCustomerId(Integer.parseInt(customerId))
                    .stream()
                    .map(OrderResponse::toResponse)
                    .collect(Collectors.toList());
        }
        return service.findAll()
                .stream()
                .map(OrderResponse::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("{customerId}/{addressId}")
    private OrderResponse createOrder(@PathVariable(name = "customerId") String customerId, @PathVariable(name = "addressId") String addressId) {
        return OrderResponse.toResponse(service.createOrder(Integer.parseInt(customerId), Integer.parseInt(addressId)));
    }

    @PutMapping("{orderProductId}/{statusCode}")
    private OrderResponse updateOrderProductStatus(@PathVariable(name = "orderProductId")String orderProductId,@PathVariable(name = "statusCode") String statusCode){
        return OrderResponse.toResponse(service.updateOrderProduct(Integer.parseInt(orderProductId),Integer.parseInt(statusCode)));
    }

    @DeleteMapping("{id}")
    private Void deleteOrder(@PathVariable String id){
        return service.deleteOrder(Integer.parseInt(id));
    }

}
