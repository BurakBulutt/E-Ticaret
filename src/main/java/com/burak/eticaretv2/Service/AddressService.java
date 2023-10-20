package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> findAll();
    AddressDto findById(Integer id);
    List<AddressDto> findByCustomerId(Integer customerId);
    AddressDto save(AddressDto dto);
    AddressDto update(Integer id, AddressDto dto);
    Void delete(Integer id);
}
