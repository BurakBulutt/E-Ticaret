package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.Dto.ShopDto;
import com.burak.eticaretv2.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ShopResponse extends BaseResponse {
    private Integer id;
    private String name;
    private String telNo;
    private List<ProductDto> productList;

    public static ShopResponse toResponse(ShopDto dto) {
        return ShopResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .telNo(dto.getTelNo())
                .productList(ProductDto.toDtoList(dto.getProductList()))
                .build();
    }

}
