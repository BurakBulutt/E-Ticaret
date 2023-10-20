package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Request.AddCategoryRequest;
import com.burak.eticaretv2.Request.AddShopRequest;
import com.burak.eticaretv2.Request.UpdateCategoryRequest;
import com.burak.eticaretv2.Request.UpdateShopRequest;
import com.burak.eticaretv2.Response.CategoryResponse;
import com.burak.eticaretv2.Response.ShopResponse;
import com.burak.eticaretv2.Service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shops")
public class ShopController {
    @Autowired
    private ShopService service;

    @GetMapping
    private List<ShopResponse> getCategories(@RequestParam(required = false) String id){
        if (id!=null){
            return Collections.singletonList(ShopResponse.toResponse(service.findById(Integer.parseInt(id))));
        }
        return service.findAll()
                .stream()
                .map(dto -> ShopResponse.toResponse(dto))
                .collect(Collectors.toList());
    }

    @PostMapping
    private ShopResponse save(@RequestBody AddShopRequest request){
        return ShopResponse.toResponse(service.save(request.toDto()));
    }

    @PutMapping("{id}")
    private ShopResponse update(@PathVariable String id, @RequestBody UpdateShopRequest request){
        return ShopResponse.toResponse(service.update(Integer.parseInt(id),request.toDto()));
    }
    @DeleteMapping("{id}")
    private Void delete(@PathVariable String id){
        return service.delete(Integer.parseInt(id));
    }
}
