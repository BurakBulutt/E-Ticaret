package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.AddressDto;
import com.burak.eticaretv2.Dto.CustomerDto;
import com.burak.eticaretv2.entity.Address;
import com.burak.eticaretv2.entity.Basket;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CustomerResponse extends BaseResponse {
    private Integer id;
    private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String email;
    private String password;
    private List<AddressDto> addressList;

    public static CustomerResponse toResponse(CustomerDto dto){
        return CustomerResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .birthDate(dto.getBirthDate())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .addressList(AddressDto.toDtoList(dto.getAddressList()))
                .build();
    }
}
