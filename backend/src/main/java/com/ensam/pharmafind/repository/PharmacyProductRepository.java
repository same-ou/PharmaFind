package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.PharmacyProduct;
import com.ensam.pharmafind.entities.PharmacyProductId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyProductRepository extends JpaRepository<PharmacyProduct, PharmacyProductId> {
    Page<PharmacyProduct> findByPharmacyId(Integer pharmacyId, Pageable pageable);
    Page<PharmacyProduct> findByProductId(Integer productId, Pageable pageable);
    PharmacyProduct findByPharmacyIdAndProductId(Integer pharmacyId, Integer productId);
}
