package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Pharmacy;
import com.ensam.pharmafind.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {
    Page<Pharmacy> findByName(String name, Pageable pageable);
    Pharmacy findByPharmacist(User pharmacist);
    Pharmacy findByPharmacistId(Integer pharmacistId);
}
