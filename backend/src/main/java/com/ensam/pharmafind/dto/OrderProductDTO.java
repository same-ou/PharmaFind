package com.ensam.pharmafind.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class OrderProductDTO {
    private Integer productId;
    private Integer pharmacyId;
    private Integer quantity;
    private Double price;
}
