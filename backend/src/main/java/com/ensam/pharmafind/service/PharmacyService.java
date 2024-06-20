package com.ensam.pharmafind.service;

import com.ensam.pharmafind.entities.Pharmacy;
import com.ensam.pharmafind.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public void addPharmacy(Pharmacy pharmacy){
        pharmacyRepository.save(pharmacy);
    }
}
