package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.CustomerDto;
import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private String name;
    private String surname;
    private String password;

    public CustomerDto toDto(){
        return CustomerDto.builder()
                .name(name)
                .surname(surname)
                .password(password)
                .build();
    }
}
