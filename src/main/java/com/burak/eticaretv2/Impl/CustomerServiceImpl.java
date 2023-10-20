package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.CustomerDto;
import com.burak.eticaretv2.Service.CustomerService;
import com.burak.eticaretv2.entity.Customer;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public List<CustomerDto> findAll() {
        return CustomerDto.toDtoList(repository.findAll());
    }

    @Override
    public CustomerDto findById(Integer id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get().toDto();
        }
        throw new NotFoundException("Seçili Id'ye Ait Müşteri Bulunamadı : " + id);
    }

    @Override
    public CustomerDto register(CustomerDto dto) {
        return repository.save(dto.toEntity()).toDto();
    }

    @Override
    public CustomerDto login(String email, String password) {
        return null;
    }

    @Override
    public CustomerDto update(Integer id, CustomerDto dto) {
        Optional<Customer> updatedCustomer = repository.findById(id);
        if (updatedCustomer.isPresent()) {
            Customer customer = repository.save(
                    updatedCustomer.get().builder()
                            .id(updatedCustomer.get().getId())
                            .name(dto.getName() != null ? dto.getName() : updatedCustomer.get().getName())
                            .surname(dto.getSurname() != null ? dto.getSurname() : updatedCustomer.get().getSurname())
                            .password(dto.getPassword() != null ? dto.getPassword() : updatedCustomer.get().getPassword())
                            .birthDate(updatedCustomer.get().getBirthDate())
                            .email(updatedCustomer.get().getEmail())
                            .addressList(updatedCustomer.get().getAddressList())
                            .build()
            );
            return customer.toDto();
        }
        throw new NotFoundException("Bu id'ye Ait Güncellenicek Müşteri Bulunamadı : " + id);
    }

    @Override
    public Void delete(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Silinecek Müşteri bulunamadı : " + id);
    }

    @Override
    public Customer getById(Integer id) {
        return repository.findById(id).isPresent() ? repository.findById(id).get():null;
    }
}
