package com.ensam.pharmafind.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private List<String> imageUrl;
}
