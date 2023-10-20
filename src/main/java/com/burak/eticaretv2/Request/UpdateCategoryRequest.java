package com.burak.eticaretv2.Request;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UpdateCategoryRequest {
    private String name;

    public UpdateCategoryRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public CategoryDto toDto() {
        return CategoryDto.builder()
                .name(name)
                .build();
    }
}
