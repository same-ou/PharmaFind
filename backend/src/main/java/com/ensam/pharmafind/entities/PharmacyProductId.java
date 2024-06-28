package com.ensam.pharmafind.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class PharmacyProductId {
    private Integer pharmacyId;
    private Integer productId;
}
