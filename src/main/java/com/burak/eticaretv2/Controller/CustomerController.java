package com.burak.eticaretv2.Controller;

import com.burak.eticaretv2.Request.AddCustomerRequest;
import com.burak.eticaretv2.Request.UpdateCustomerRequest;
import com.burak.eticaretv2.Response.CustomerResponse;
import com.burak.eticaretv2.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping
    private List<CustomerResponse> getCustomers(@RequestParam(required = false) String id) {
        if (id != null) {
            return Collections.singletonList(CustomerResponse.toResponse(service.findById(Integer.parseInt(id))));
        }
        return service.findAll()
                .stream()
                .map(dto -> CustomerResponse.toResponse(dto))
                .collect(Collectors.toList());
    }

    @PostMapping
    private CustomerResponse register(@RequestBody AddCustomerRequest request){
        return CustomerResponse.toResponse(service.register(request.toDto()));
    }

    @PutMapping("{id}")
    private CustomerResponse update(@PathVariable String id, @RequestBody UpdateCustomerRequest request){
        return CustomerResponse.toResponse(service.update(Integer.parseInt(id),request.toDto()));
    }

    @DeleteMapping("{id}")
    private Void delete(@PathVariable String id){
        return service.delete(Integer.parseInt(id));
    }


}
