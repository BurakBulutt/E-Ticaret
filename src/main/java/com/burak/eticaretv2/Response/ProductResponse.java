package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.Dto.ShopDto;
import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Shop;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse extends BaseResponse{
    private Integer id;
    private String name;
    private Double unitPrice;
    private Integer unitsInStock;
    private ShopDto shop;
    private CategoryDto category;

    public static ProductResponse toResponse(ProductDto dto){
        return ProductResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .unitPrice(dto.getUnitPrice())
                .unitsInStock(dto.getUnitsInStock())
                .shop(dto.getShop().toDto())
                .category(dto.getCategory().toDto())
                .build();
    }


}
