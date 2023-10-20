package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Request.AddCategoryRequest;
import com.burak.eticaretv2.Request.UpdateCategoryRequest;
import com.burak.eticaretv2.Response.CategoryResponse;
import com.burak.eticaretv2.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping
    private List<CategoryResponse> getCategories(@RequestParam(required = false) String id){
        if (id!=null){
            return Collections.singletonList(CategoryResponse.toResponse(service.findById(Integer.parseInt(id))));
        }
        return service.findAll()
                .stream()
                .map(dto -> CategoryResponse.toResponse(dto))
                .collect(Collectors.toList());
    }

    @PostMapping
    private CategoryResponse save(@RequestBody AddCategoryRequest request){
        return CategoryResponse.toResponse(service.save(request.toDto()));
    }

    @PutMapping("{id}")
    private CategoryResponse update(@PathVariable String id, @RequestBody UpdateCategoryRequest request){
        return CategoryResponse.toResponse(service.update(Integer.parseInt(id),request.toDto()));
    }
    @DeleteMapping("{id}")
    private Void delete(@PathVariable String id){
        return service.delete(Integer.parseInt(id));
    }
}
