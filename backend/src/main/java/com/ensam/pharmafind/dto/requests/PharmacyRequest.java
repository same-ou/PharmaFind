package com.ensam.pharmafind.dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PharmacyRequest {
    private Integer id;
    private String name;
    private String telephone;
}
