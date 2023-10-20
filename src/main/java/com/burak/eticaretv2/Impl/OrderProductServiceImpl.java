package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Service.OrderProductService;
import com.burak.eticaretv2.entity.OrderProduct;
import com.burak.eticaretv2.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepository repository;

    @Override
    public OrderProduct findById(Integer id) {
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @Override
    public OrderProduct save(OrderProduct orderProduct) {
        return repository.save(orderProduct);
    }


}
