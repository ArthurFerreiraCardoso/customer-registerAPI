package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.dtos.CustomerDTORequest;
import com.example.api.mapper.CustomerMapper;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository repository;


    public List<Customer> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public Optional<Customer> findById(Long id) {
        return repository.findById(id);
    }

    public CustomerDTORequest saveCustomer(CustomerDTORequest customerDTORequest) {
        Customer customer = customerMapper.toEntity(customerDTORequest);
        Objects.requireNonNull(customer, "Error validating customer: " + customer.getName());

        Customer savedCustomer = repository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDTORequest updateCustomer(long id, CustomerDTORequest customerDTORequest) throws ResponseStatusException {
        Customer idCustomer = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + id));

        Customer customerUpdate = customerMapper.toEntity(customerDTORequest);

        idCustomer.setName(customerUpdate.getName());
        idCustomer.setEmail(customerUpdate.getEmail());

        Customer customerSaved = repository.save(customerUpdate);
        return customerMapper.toDto(customerSaved);
    }


    public void delete(Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with ID: " + id));
        repository.delete(customer);
    }

    public Page<Customer> getCustomers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }
}

