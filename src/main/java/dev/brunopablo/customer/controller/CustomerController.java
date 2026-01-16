package dev.brunopablo.customer.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.brunopablo.customer.controller.dto.ApiResponse;
import dev.brunopablo.customer.controller.dto.CreateCustomerRequest;
import dev.brunopablo.customer.controller.dto.PaginationRequest;
import dev.brunopablo.customer.controller.dto.UpdateCustomerRequest;
import dev.brunopablo.customer.entity.CustomerEntity;
import dev.brunopablo.customer.service.CustomerService;

@RestController
@RequestMapping(value="/customers")
public class CustomerController {
    
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @PostMapping
    public ResponseEntity<String> createCustomer(
        @RequestBody CreateCustomerRequest createCustomerRequest
    ){
        
        var customerEntity = customerService.createCustomer(createCustomerRequest);
        
        return ResponseEntity.created(URI.create("/customers/" + customerEntity.getId())).build();
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<CustomerEntity>> listCustomer(
        @RequestParam(name="pageNumber", defaultValue="0") Integer pageNumber,
        @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
        @RequestParam(name="orderBy", defaultValue="desc") String orderBy,
        @RequestParam(name="email", required=false) String email,
        @RequestParam(name="cpf", required=false) String cpf
    ){
        
        var pages = customerService.listCustomers(pageNumber, pageSize, orderBy, email, cpf);
        
        return ResponseEntity.ok(
            new ApiResponse<>(
                pages.getContent(),
                new PaginationRequest(
                    pages.getNumber(),
                    pages.getSize(),
                    pages.getTotalElements(),
                    pages.getTotalPages()
                )
            )
        );
    }
    
    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(
        @PathVariable("customerId") UUID customerId,
        @RequestBody UpdateCustomerRequest updateCustomerRequest
    ){
        
        var customer = customerService.updateCustomer(customerId, updateCustomerRequest);
        
        return customer.isPresent() 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") UUID customerId){

        var customerExists = customerService.deleteUserById(customerId);
        
        return customerExists 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.notFound().build();
    }
}