package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.ShopDto;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UpdateShopRequest {
    private String name;
    private String telNo;

    public ShopDto toDto() {
        return ShopDto.builder()
                .name(name)
                .telNo(telNo)
                .build();
    }
}
