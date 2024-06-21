package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByPharmacyId(Integer pharmacyId, Pageable pageable);
}
