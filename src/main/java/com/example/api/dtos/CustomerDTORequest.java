package com.example.api.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerDTORequest {
    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
}
