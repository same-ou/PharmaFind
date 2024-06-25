package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    Page<Pharmacy> findByName(String name, Pageable pageable);

}
