package com.burak.eticaretv2.Impl;

import com.burak.eticaretv2.Dto.CategoryDto;
import com.burak.eticaretv2.Dto.ProductDto;
import com.burak.eticaretv2.Dto.ShopDto;
import com.burak.eticaretv2.Service.CategoryService;
import com.burak.eticaretv2.Service.ProductService;
import com.burak.eticaretv2.Service.ShopService;
import com.burak.eticaretv2.entity.Product;
import com.burak.eticaretv2.exception.NotFoundException;
import com.burak.eticaretv2.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ShopService shopService;


    @Override
    public List<ProductDto> findAll() {
        return ProductDto.toDtoList(repository.findAll());
    }

    @Override
    public List<ProductDto> findProductsByCategoryId(Integer categoryId) {
        if (categoryService.findById(categoryId) == null) {
            throw new NotFoundException("Kategori Bulunamadı : " + categoryId);
        }
        return ProductDto.toDtoList(repository.findProductsByCategory_Id(categoryId));
    }

    @Override
    public List<ProductDto> findProductsByShopId(Integer shopId) {
        if (shopService.findById(shopId) == null) {
            throw new NotFoundException("Kategori Bulunamadı : " + shopId);
        }
        return ProductDto.toDtoList(repository.findProductsByShop_Id(shopId));
    }

    @Override
    public ProductDto findById(Integer id) {
        if (repository.findById(id).isEmpty()) {
            throw new NotFoundException("Seçili Idye Ait Ürün Yok : " + id);
        }
        return repository.findById(id).get().toDto();
    }

    @Override
    public ProductDto save(ProductDto dto) {
        return checkIfPresent(dto);
    }

    private ProductDto checkIfPresent(ProductDto dto) {
        Optional<Product> optionalProduct = repository.findProductByCategory_IdAndShop_IdAndName(dto.getCategory().getId(), dto.getShop().getId(), dto.getName());
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setUnitsInStock(optionalProduct.get().getUnitsInStock() + dto.getUnitsInStock());
            repository.save(optionalProduct.get());
            return optionalProduct.get().toDto();
        }
        dto.setShop(shopService.getById(dto.getShop().getId()));
        dto.setCategory(categoryService.getById(dto.getCategory().getId()));
        Product product = repository.save(dto.toEntity());
        shopService.getById(dto.getShop().getId()).getProductList().add(product);
        categoryService.getById(dto.getCategory().getId()).getProductList().add(product);
        return product.toDto();
    }

    @Override
    public ProductDto update(Integer id, ProductDto dto) {
        Optional<Product> updatedProduct = repository.findById(id);
        if (updatedProduct.isPresent()) {
            Product product = repository.save(
                    updatedProduct.get().builder()
                            .id(updatedProduct.get().getId())
                            .name(dto.getName() != null ? dto.getName() : updatedProduct.get().getName())
                            .unitPrice(dto.getUnitPrice() != null ? dto.getUnitPrice() : updatedProduct.get().getUnitPrice())
                            .unitsInStock(dto.getUnitsInStock() != null ? dto.getUnitsInStock() : updatedProduct.get().getUnitsInStock())
                            .shop(updatedProduct.get().getShop())
                            .category(updatedProduct.get().getCategory())
                            .build()
            );
            return product.toDto();
        }
        throw new NotFoundException("Güncellenecek Product Bulunamadı : " + id);
    }

    @Override
    public Void delete(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return null;
        }
        throw new NotFoundException("Silinecek Product Bulunamadı : " + id);
    }

    @Override
    public Product getById(Integer id) {
        if (repository.findById(id).isPresent()){
            return repository.findById(id).get();
        }
        throw new NotFoundException("Product Bulunamadı : " + id);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }
}
