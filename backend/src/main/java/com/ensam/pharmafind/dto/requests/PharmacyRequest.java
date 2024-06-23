package com.ensam.pharmafind.dto.requests;


import com.ensam.pharmafind.dto.AddressDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
public class PharmacyRequest {
    @NotEmpty(message = "field name cannot be empty")
    private String name;
    @NotEmpty
    private String telephone;
    private AddressDTO address;
}
