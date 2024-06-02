package com.example.api.mapper;

import com.example.api.domain.Customer;
import com.example.api.dtos.CustomerDTORequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

   Customer toEntity(CustomerDTORequest customerDTORequest);

   CustomerDTORequest toDto(Customer customer);
}
