package com.burak.eticaretv2.Response;

import com.burak.eticaretv2.Dto.CategoryDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryResponse {
    private Integer id;
    private String name;

    public static CategoryResponse toResponse(CategoryDto dto) {
        return CategoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
