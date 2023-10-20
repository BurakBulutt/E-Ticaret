package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.entity.OrderProduct;

public interface OrderProductService {
    OrderProduct findById(Integer id);
    OrderProduct save(OrderProduct orderProduct);
}
