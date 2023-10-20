package com.burak.eticaretv2.Dto;

import com.burak.eticaretv2.entity.Address;
import com.burak.eticaretv2.entity.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class AddressDto {
    private Integer id;
    private String city;
    private String district;
    private String postalCode;
    private String addressText;
    private CustomerDto customer;

    public static List<AddressDto> toDtoList(List<Address> addressList){
        return addressList
                .stream()
                .map(address -> address.toDto())
                .collect(Collectors.toList());
    }

    public Address toEntity(){
        return Address.builder()
                .city(city)
                .district(district)
                .postalCode(postalCode)
                .addressText(addressText)
                .customer(Customer.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .surname(customer.getSurname())
                        .birthDate(customer.getBirthDate())
                        .email(customer.getEmail())
                        .password(customer.getPassword())
                        .addressList(customer.getAddressList())
                        .basketList(customer.getBasketList())
                        .build())
                .build();
    }

}
