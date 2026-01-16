package dev.brunopablo.customer.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import dev.brunopablo.customer.controller.dto.CreateCustomerRequest;
import dev.brunopablo.customer.controller.dto.UpdateCustomerRequest;
import dev.brunopablo.customer.entity.CustomerEntity;
import dev.brunopablo.customer.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity createCustomer(CreateCustomerRequest createCustomerRequest) {

        var newCustomer = new CustomerEntity(); 

        newCustomer.setName(createCustomerRequest.name());
        newCustomer.setCpf(createCustomerRequest.cpf());
        newCustomer.setEmail(createCustomerRequest.email());
        newCustomer.setPhone(createCustomerRequest.phone());

        var customerEntity = customerRepository.save(newCustomer);

        return customerEntity;
    }

    public Page<CustomerEntity> listCustomers(Integer pageNumber, 
                                                     Integer pageSize, 
                                                     String orderBy, 
                                                     String email, 
                                                     String cpf) {

        var pageRequest = getPageRequest(pageNumber, pageSize, orderBy);

        var pages = getPages(email, cpf, pageRequest);

        return pages;
    }

    private Page<CustomerEntity> getPages(String email, 
                                          String cpf, 
                                          PageRequest pageRequest) {
        
        if(email != null && cpf != null)
            return customerRepository.findByEmailAndCpf(email, cpf, pageRequest);

        if(email != null)
            return customerRepository.findByEmail(email, pageRequest);

        if(cpf != null)
            return customerRepository.findByCpf(cpf, pageRequest);

        return customerRepository.findAll(pageRequest);
    }

    public PageRequest getPageRequest(Integer pageNumber, 
                                      Integer pageSize, 
                                      String orderBy){

        Direction orderByDirection = Sort.Direction.DESC;

        if(orderBy.equalsIgnoreCase("asc"))
            orderByDirection = Sort.Direction.ASC;

        return PageRequest.of(pageNumber, pageSize, orderByDirection, "createdAt");                                     
    }

    public Optional<CustomerEntity> updateCustomer(UUID idCustomer, UpdateCustomerRequest updateCustomerRequest) {
        
        var customerEntity = customerRepository.findById(idCustomer);

        if(customerEntity.isPresent()){
            if(!updateCustomerRequest.name().equalsIgnoreCase(customerEntity.get().getName()) && updateCustomerRequest.name() != null){
                customerEntity.get().setName(updateCustomerRequest.name());
            }

            if(!updateCustomerRequest.email().equalsIgnoreCase(customerEntity.get().getEmail()) && updateCustomerRequest.email() != null){
                customerEntity.get().setEmail(updateCustomerRequest.email());
            }

            if(!updateCustomerRequest.phone().equalsIgnoreCase(customerEntity.get().getPhone()) && updateCustomerRequest.phone() != null){
                customerEntity.get().setPhone(updateCustomerRequest.phone());
            }

            customerRepository.save(customerEntity.get());
        }

        return customerEntity;
    }

    public boolean deleteUserById(UUID customerId) {
    
        var customerExists = customerRepository.findById(customerId).isPresent();

        if(customerExists)
            customerRepository.deleteById(customerId);

        return customerExists;
    }
}