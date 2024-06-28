package com.ensam.pharmafind.dto.responses;

import com.ensam.pharmafind.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PharmacyResponse {
    private Integer id;
    private String name;
    private String telephone;
    private AddressDTO address;
}
