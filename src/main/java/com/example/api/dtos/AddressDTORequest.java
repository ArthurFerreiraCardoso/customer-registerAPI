package com.example.api.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AddressDTORequest {
    @NotEmpty
    private String street;

}
