package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.entity.Shop;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Integer id);
    CategoryDto save(CategoryDto dto);
    CategoryDto update(Integer id,CategoryDto dto);
    Void delete(Integer id);

    Category getById(Integer id);
}
