package com.ensam.pharmafind.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Delivrer extends User{
    @OneToMany(mappedBy = "delivrer")
    private List<Delivery> deliveries;
}
