package com.ensam.pharmafind.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
@AllArgsConstructor
public class PharmacyProductId {
    private Integer pharmacyId;
    private Integer productId;
}
