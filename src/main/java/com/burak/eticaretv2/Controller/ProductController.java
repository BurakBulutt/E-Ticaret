package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Request.AddProductRequest;
import com.burak.eticaretv2.Request.UpdateProductRequest;
import com.burak.eticaretv2.Response.ProductResponse;
import com.burak.eticaretv2.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    private List<ProductResponse> getProducts(@RequestParam(required = false) String id,
                                              @RequestParam(required = false) String categoryId,
                                              @RequestParam(required = false) String shopId) {
        if (id != null) {
            Collections.singletonList(ProductResponse.toResponse(service.findById(Integer.parseInt(id))));
        } else if (categoryId != null) {
            return service.findProductsByCategoryId(Integer.parseInt(categoryId))
                    .stream()
                    .map(dto -> ProductResponse.toResponse(dto))
                    .collect(Collectors.toList());

        } else if (shopId != null) {
            return service.findProductsByShopId(Integer.parseInt(shopId))
                    .stream()
                    .map(dto -> ProductResponse.toResponse(dto))
                    .collect(Collectors.toList());
        }
        return service.findAll()
                .stream()
                .map(dto -> ProductResponse.toResponse(dto))
                .collect(Collectors.toList());
    }

    @PostMapping
    private ProductResponse save(@RequestBody AddProductRequest request){
        return ProductResponse.toResponse(service.save(request.toDto()));
    }

    @PutMapping("{id}")
    private ProductResponse update(@PathVariable String id,@RequestBody UpdateProductRequest request){
        return ProductResponse.toResponse(service.update(Integer.parseInt(id),request.toDto()));
    }

    @DeleteMapping("{id}")
    private Void delete(@PathVariable String id){
        return service.delete(Integer.parseInt(id));
    }

}
