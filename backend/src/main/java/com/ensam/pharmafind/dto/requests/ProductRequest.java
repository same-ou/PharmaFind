package com.ensam.pharmafind.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
}
