package com.example.api.mapper;

import com.example.api.domain.Address;
import com.example.api.dtos.AddressDTORequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

   Address toEntity(AddressDTORequest addressDTORequest);

   AddressDTORequest toDto(Address address);
}
