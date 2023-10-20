package com.burak.eticaretv2.entity;

import com.burak.eticaretv2.Dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
public class Customer extends AbstractEntity {
    private String name;
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    private String email;
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Basket> basketList;

    public CustomerDto toDto(){
        return CustomerDto.builder()
                .id(getId())
                .name(getName())
                .surname(getSurname())
                .birthDate(getBirthDate())
                .email(getEmail())
                .password(getPassword())
                .addressList(getAddressList())
                .basketList(getBasketList())
                .build();
    }
}
