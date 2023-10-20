package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.CustomerDto;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Builder;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Data
public class AddCustomerRequest {
    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String password;

    public CustomerDto toDto(){
        return CustomerDto.builder()
                .name(name)
                .surname(surname)
                .birthDate(birthDate.transform(string -> {
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date date = format.parse(string);
                        return date;
                    } catch (ParseException e) {
                        throw new RuntimeException("HATALI TARIH FORMATI ! : " + e);
                    }
                }))
                .password(password)
                .email(email)
                .basketList(new ArrayList<>())
                .addressList(new ArrayList<>())
                .build();
    }
}
