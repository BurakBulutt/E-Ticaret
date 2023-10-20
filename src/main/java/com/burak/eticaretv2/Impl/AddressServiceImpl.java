package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.AddressDto;
import com.burak.eticaretv2.Service.AddressService;
import com.burak.eticaretv2.Service.CustomerService;
import com.burak.eticaretv2.entity.Address;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository repository;
    @Autowired
    private CustomerService customerService;

    @Override
    public List<AddressDto> findAll() {
        return AddressDto.toDtoList(repository.findAll());
    }

    @Override
    public AddressDto findById(Integer id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get().toDto();
        }
        throw new NotFoundException("Seçili Id'ye Ait Adres Bulunamadı : " + id);
    }

    @Override
    public List<AddressDto> findByCustomerId(Integer customerId) {

        if (customerService.getById(customerId) == null) {
            throw new NoSuchElementException("Girilen Müşteri Id'sine Ait Kullanıcı Bulunmuyor : " + customerId);
        }

        if (repository.findAddressesByCustomerId(customerId) == null) {
            throw new NotFoundException("Girilen Müşteri Id'sine Ait Adres Bulunmuyor : " + customerId);
        }
        return AddressDto.toDtoList(repository.findAddressesByCustomerId(customerId));

    }

    @Override
    public AddressDto save(AddressDto dto) {
        dto.setCustomer(customerService.getById(dto.getCustomer().getId()).toDto());
        if (dto.getCustomer() == null) {
            throw new NotFoundException("Bu Id'ye Ait Kullanıcı Bulunamadı : ");
        }
        Address address = dto.toEntity();
        customerService.findById(dto.getCustomer().getId()).getAddressList().add(address);
        repository.save(address);
        return address.toDto();
    }

    @Override
    public AddressDto update(Integer id, AddressDto dto) {
        Optional<Address> updatedAddress = repository.findById(id);
        if (updatedAddress.isPresent()) {
            Address address = repository.save(
                    updatedAddress.get().builder()
                            .id(updatedAddress.get().getId())
                            .city(dto.getCity() != null ? dto.getCity() : updatedAddress.get().getCity())
                            .district(dto.getDistrict() != null ? dto.getDistrict() : updatedAddress.get().getDistrict())
                            .postalCode(dto.getPostalCode() != null ? dto.getPostalCode() : updatedAddress.get().getPostalCode())
                            .addressText(dto.getAddressText() != null ? dto.getAddressText() : updatedAddress.get().getAddressText())
                            .customer(updatedAddress.get().getCustomer())
                            .build()
            );
            return address.toDto();
        }
        throw new NotFoundException("Güncellenecek Adres Bulunamadı : " + id);
    }

    @Override
    public Void delete(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Silinecek Adres Bulunamadı : " + id);
    }
}
