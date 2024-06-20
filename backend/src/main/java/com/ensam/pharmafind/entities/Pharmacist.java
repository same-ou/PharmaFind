package com.ensam.pharmafind.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Pharmacist extends User {

    @OneToOne(mappedBy = "pharmacist")
    private Pharmacy pharmacy;
}
