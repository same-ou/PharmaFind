package com.ensam.pharmafind.entities;

import jakarta.persistence.*;

@Entity
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "delivrer_id")
    private User delivrer;
}
