package com.ensam.pharmafind.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class PharmacyProductId {
    private Integer pharmacyId;
    private Integer productId;
}
