package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer>{
}
