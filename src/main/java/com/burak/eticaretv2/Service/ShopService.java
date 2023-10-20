package com.burak.eticaretv2.Service;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.Dto.ShopDto;
import com.burak.eticaretv2.entity.Shop;

import java.util.List;

public interface ShopService {
    List<ShopDto> findAll();
    ShopDto findById(Integer id);
    ShopDto save(ShopDto dto);
    ShopDto update(Integer id, ShopDto dto);
    Void delete(Integer id);

    Shop getById(Integer id);

}
