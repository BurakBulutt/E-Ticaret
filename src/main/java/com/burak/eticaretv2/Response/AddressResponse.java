package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.AddressDto;
import com.burak.eticaretv2.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse extends BaseResponse{
    private Integer id;
    private String city;
    private String district;
    private String postalCode;
    private String addressText;
    private Integer customerId;

    public static AddressResponse toResponse(AddressDto dto){
        return AddressResponse.builder()
                .id(dto.getId())
                .city(dto.getCity())
                .district(dto.getDistrict())
                .postalCode(dto.getPostalCode())
                .addressText(dto.getAddressText())
                .customerId(dto.getCustomer().getId())
                .build();
    }
}
