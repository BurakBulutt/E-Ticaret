package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.Service.CategoryService;
import com.burak.eticaretv2.entity.Category;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Override
    public List<CategoryDto> findAll() {
        return CategoryDto.toDtoList(repository.findAll());
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get().toDto();
        }
        throw new NotFoundException("Seçili Id'ye Ait Kategori Bulunamadı : " + id);
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        return repository.save(dto.toEntity()).toDto();
    }

    @Override
    public CategoryDto update(Integer id, CategoryDto dto) {
        Optional<Category> updatedCategory = repository.findById(id);
        if (updatedCategory.isPresent()) {
            Category category = repository.save(
                    updatedCategory.get().builder()
                            .id(updatedCategory.get().getId())
                            .name(dto.getName() != null ? dto.getName() : updatedCategory.get().getName())
                            .productList(updatedCategory.get().getProductList())
                            .build()
            );
            return category.toDto();
        }
        throw new NotFoundException("Seçili Id'ye Ait Kategori Bulunamadı : " + id);
    }

    @Override
    public Void delete(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Seçili Id'ye Ait Kategori Bulunamadı : " + id);
    }

    @Override
    public Category getById(Integer id) {
        return repository.findById(id).get();
    }
}
