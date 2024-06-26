package com.ensam.pharmafind.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PharmacyProduct {
    @EmbeddedId
    private PharmacyProductId id;

    @ManyToOne
    @MapsId("pharmacyId")
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double price;
}
