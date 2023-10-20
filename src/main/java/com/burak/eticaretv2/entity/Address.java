package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class Address extends AbstractEntity {
    private String city;
    private String district;
    private String postalCode;
    private String addressText;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    public AddressDto toDto(){
        return AddressDto.builder()
                .id(getId())
                .city(getCity())
                .district(getDistrict())
                .postalCode(getPostalCode())
                .addressText(getAddressText())
                .customer(getCustomer().toDto())
                .build();
    }

}
