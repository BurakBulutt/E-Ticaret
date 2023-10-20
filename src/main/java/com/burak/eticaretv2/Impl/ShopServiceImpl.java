package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.ShopDto;
import com.burak.eticaretv2.Service.ShopService;
import com.burak.eticaretv2.entity.Shop;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository repository;

    @Override
    public List<ShopDto> findAll() {
        return ShopDto.toDtoList(repository.findAll());
    }

    @Override
    public ShopDto findById(Integer id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get().toDto();
        }
        throw new NotFoundException("Seçili Id'ye Ait Market Bulunamadı : " + id);
    }

    @Override
    public ShopDto save(ShopDto dto) {
        return repository.save(dto.toEntity()).toDto();
    }

    @Override
    public ShopDto update(Integer id, ShopDto dto) {
        Optional<Shop> updatedShop = repository.findById(id);
        if (updatedShop.isPresent()) {
            Shop shop = repository.save(
                    updatedShop.get().builder()
                            .id(updatedShop.get().getId())
                            .name(dto.getName() != null ? dto.getName() : updatedShop.get().getName())
                            .telNo(dto.getTelNo() != null ? dto.getTelNo() : updatedShop.get().getTelNo())
                            .productList(updatedShop.get().getProductList())
                            .build()
            );
            return shop.toDto();
        }
        throw new NotFoundException("Seçili Id'ye Ait Market Bulunamadı : " + id);
    }

    @Override
    public Void delete(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Seçili Id'ye Ait Market Bulunamadı : " + id);
    }

    @Override
    public Shop getById(Integer id) {
        return repository.findById(id).get();
    }
}
