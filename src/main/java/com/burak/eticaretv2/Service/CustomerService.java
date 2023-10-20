package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.CustomerDto;
import com.burak.eticaretv2.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> findAll();
    CustomerDto findById(Integer id);
    CustomerDto register(CustomerDto dto);
    CustomerDto login(String email,String password);
    CustomerDto update(Integer id, CustomerDto dto);
    Void delete(Integer id);

    Customer getById(Integer id);

}
