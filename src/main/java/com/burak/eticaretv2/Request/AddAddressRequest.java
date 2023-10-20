package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.AddressDto;
import com.burak.eticaretv2.Dto.CustomerDto;
import com.burak.eticaretv2.entity.Customer;
import lombok.Data;

@Data
public class AddAddressRequest {
    private String city;
    private String district;
    private String postalCode;
    private String addressText;
    private Integer customerId;

    public AddressDto toDto(){
        return AddressDto.builder()
                .city(city)
                .district(district)
                .postalCode(postalCode)
                .addressText(addressText)
                .customer(CustomerDto.builder().id(customerId).build())
                .build();
    }
}
