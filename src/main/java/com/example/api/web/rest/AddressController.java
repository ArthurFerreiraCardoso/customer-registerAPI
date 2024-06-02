package com.example.api.web.rest;

import com.example.api.domain.Address;
import com.example.api.dtos.AddressDTORequest;
import com.example.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("/{customerId}/save-address")
    public ResponseEntity<AddressDTORequest> saveAddress(@PathVariable Long customerId, @RequestBody AddressDTORequest addressDTORequest) {
        try {
            service.saveAddress(customerId, addressDTORequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(addressDTORequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{customerId}/addresses")
    public ResponseEntity<List<Address>> getAddressesForCustomer(@PathVariable Long customerId) {
        List<Address> addresses = service.getAddressesForCustomer(customerId);
        if (addresses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(addresses);
        }
    }
}
