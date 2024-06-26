package com.ensam.pharmafind.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class ProductCreateDTO {
    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
