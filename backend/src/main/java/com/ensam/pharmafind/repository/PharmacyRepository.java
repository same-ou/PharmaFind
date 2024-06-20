package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
}
