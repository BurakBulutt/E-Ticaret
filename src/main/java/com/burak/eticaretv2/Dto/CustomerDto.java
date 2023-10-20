package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Address;
import com.burak.eticaretv2.entity.Basket;
import com.burak.eticaretv2.entity.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CustomerDto {
    private Integer id;
    private String name;
    private String surname;
    private Date birthDate;
    private String email;
    private String password;
    @JsonIgnore
    private List<Address> addressList;
    @JsonIgnore
    private List<Basket> basketList;


    public static List<CustomerDto> toDtoList(List<Customer> customerList) {
        return customerList
                .stream()
                .map(customer -> customer.toDto())
                .collect(Collectors.toList());
    }

    public Customer toEntity() {
        return Customer.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .birthDate(birthDate)
                .email(email)
                .password(password)
                .addressList(addressList)
                .basketList(basketList)
                .build();
    }
}
