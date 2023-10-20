package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Request.AddAddressRequest;
import com.burak.eticaretv2.Request.UpdateAddressRequest;
import com.burak.eticaretv2.Response.AddressResponse;
import com.burak.eticaretv2.Service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("addresses")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private AddressService service;

    @GetMapping
    private List<AddressResponse> getAddresses(@RequestParam(required = false) String id,
                                               @RequestParam(required = false) String customerId){
        if (id != null){
            return Collections.singletonList(AddressResponse.toResponse(service.findById(Integer.parseInt(id))));
        } else if (customerId!=null) {
            return service.findByCustomerId(Integer.parseInt(customerId))
                    .stream()
                    .map(AddressResponse::toResponse)
                    .collect(Collectors.toList());
        }
        return service.findAll()
                .stream()
                .map(AddressResponse::toResponse)
                .collect(Collectors.toList());
    }

    @PostMapping
    private AddressResponse save(@RequestBody AddAddressRequest request){
        return AddressResponse.toResponse(service.save(request.toDto()));
    }

    @PutMapping("{id}")
    private AddressResponse update(@PathVariable String id, @RequestBody UpdateAddressRequest request){
        return AddressResponse.toResponse(service.update(Integer.parseInt(id),request.toDto()));
    }

    @DeleteMapping("{id}")
    private Void delete(@PathVariable String id){
        return service.delete(Integer.parseInt(id));
    }
}
