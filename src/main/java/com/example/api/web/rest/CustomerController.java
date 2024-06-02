package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.dtos.CustomerDTORequest;
import com.example.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @PostMapping(value = "/save-customer")
    public ResponseEntity<CustomerDTORequest> saveCustomer(@RequestBody CustomerDTORequest customerDTORequest) {
        try {
            service.saveCustomer(customerDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(customerDTORequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerDTORequest);
        }
    }

    @PutMapping(value = "/update-customer/{id}")
    public  ResponseEntity<CustomerDTORequest> updateCustomer(@PathVariable long id, @RequestBody CustomerDTORequest customerDTORequest) throws ResponseStatusException {
        service.updateCustomer(id, customerDTORequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTORequest);
    }

    @DeleteMapping(value = "/delete-customer/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ResponseStatusException {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //http://localhost:8080/customers/page-customers?page=0&size=10
    @GetMapping("/page-customers")
    public ResponseEntity<Page<Customer>> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Customer> customers = service.getCustomers(page, size);
        return ResponseEntity.ok(customers);
    }


    @GetMapping(value = "/customer-list")
    public List<Customer> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }


}
