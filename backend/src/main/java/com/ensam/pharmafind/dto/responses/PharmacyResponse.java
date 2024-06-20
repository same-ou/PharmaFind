package com.ensam.pharmafind.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PharmacyResponse {
    private Integer id;
    private String name;
    private String telephone;
}
