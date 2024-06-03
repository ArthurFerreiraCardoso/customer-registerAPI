package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dtos.AddressDTORequest;
import com.example.api.mapper.AddressMapper;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressMapper addressMapper;

    public AddressDTORequest saveAddress(Long customerId, AddressDTORequest addressDTORequest) {
        Address address = addressMapper.toEntity(addressDTORequest);
        Objects.requireNonNull(address, "Error validating Address: " + address.getStreet());

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));

        address.setCustomer(customer);
        repository.save(address);
        return addressMapper.toDto(address);
    }

    public List<Address> getAddressesForCustomer(Long customerId) {
        return repository.findAllByCustomerId(customerId);
    }
}

