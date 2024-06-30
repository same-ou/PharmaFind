package com.ensam.pharmafind.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Embeddable
@AllArgsConstructor @NoArgsConstructor
@Setter
public class PharmacyProductId {
    private Integer pharmacyId;
    private Integer productId;
}
