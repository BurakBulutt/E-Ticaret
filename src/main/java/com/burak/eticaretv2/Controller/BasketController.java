package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Request.AddBasketProductRequest;
import com.burak.eticaretv2.Response.BasketResponse;
import com.burak.eticaretv2.Service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("baskets")
@RequiredArgsConstructor
public class BasketController {
    @Autowired
    private BasketService service;

    @GetMapping
    private List<BasketResponse> getBaskets(@RequestParam(required = false) String id,
                                            @RequestParam(required = false) String statusCode,
                                            @RequestParam(required = false) String customerId) {
        if (id != null) {
            return Collections.singletonList(BasketResponse.toResponse(service.findById(Integer.parseInt(id))));
        } else if (customerId != null) {
            return service.findBasketsByCustomerId(Integer.parseInt(customerId))
                    .stream()
                    .map(BasketResponse::toResponse)
                    .collect(Collectors.toList());
        } else if (statusCode != null) {
            service.findBasketByStatusCode(Integer.parseInt(statusCode))
                    .stream()
                    .map(BasketResponse::toResponse)
                    .collect(Collectors.toList());
        }
        return service.findAll()
                .stream()
                .map(BasketResponse::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("{id}")
    private BasketResponse addBasketProductOnBasket(@PathVariable(name = "id") String customerId, @RequestBody AddBasketProductRequest request) {
        return BasketResponse.toResponse(service.addBasketProductOnBasket(Integer.parseInt(customerId), request.toDto()));
    }

    @PutMapping("increase/{id}")
    private BasketResponse increaseBasketProduct(@PathVariable(name = "id") String basketProductId) {
        return BasketResponse.toResponse(service.increaseBasketProductQuantity(Integer.parseInt(basketProductId)));
    }

    @PutMapping("decrease/{id}")
    private BasketResponse decreaseBasketProduct(@PathVariable(name = "id") String basketProductId) {
        return BasketResponse.toResponse(service.decreaseBasketProductQuantity(Integer.parseInt(basketProductId)));
    }

    @DeleteMapping("{basketProductId}")
    private Void deleteBasketProduct(@PathVariable String basketProductId) {
        return service.deleteBasketProductOnBasket(Integer.parseInt(basketProductId));
    }
}
