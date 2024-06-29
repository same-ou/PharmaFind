package com.ensam.pharmafind.dto.requests;

import com.ensam.pharmafind.dto.CategoryDTO;
import com.ensam.pharmafind.dto.ImageDTO;
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
    private Integer quantity;
    private Integer pharmacyId;
    private List<ImageDTO> images;
    private List<Integer> categories;
}
