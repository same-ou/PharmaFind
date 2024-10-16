package com.ensam.pharmafind.service;

import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.entities.Address;
import com.ensam.pharmafind.entities.Pharmacy;
import com.ensam.pharmafind.entities.User;
import com.ensam.pharmafind.dto.mappers.PharmacyMapper;
import com.ensam.pharmafind.repository.AddressRepository;
import com.ensam.pharmafind.repository.PharmacyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {
   private final PharmacyRepository pharmacyRepository;
   private final AddressRepository addressRepository;

   @Transactional
    public PharmacyResponse savePharmacy(
            PharmacyRequest pharmacyRequest,
            Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Pharmacy pharmacy = PharmacyMapper.INSTANCE.toPharmacy(pharmacyRequest);
        Address address = addressRepository.save(pharmacy.getAddress());
        pharmacy.setAddress(address);
        pharmacy.setPharmacist(user);
        Pharmacy pharmacy1 =  pharmacyRepository.save(pharmacy);
        return PharmacyMapper.INSTANCE.toPharmacyResponse(pharmacy1);
    }

    public PharmacyResponse getPharmacy(Integer id){
        return pharmacyRepository.findById(id)
                .map(PharmacyMapper.INSTANCE::toPharmacyResponse)
                .orElseThrow(()-> new EntityNotFoundException("Pharmacy not found"));
    }

    public PageResponse<PharmacyResponse> getPharmacies(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Pharmacy> pharmacies = pharmacyRepository.findAll(pageable);
        List<PharmacyResponse> pharmacyResponses = pharmacies.stream()
                .map(PharmacyMapper.INSTANCE::toPharmacyResponse)
                .toList();

        return new PageResponse<>(
                pharmacyResponses,
                pharmacies.getNumber(),
                pharmacies.getSize(),
                pharmacies.getTotalElements(),
                pharmacies.getTotalPages(),
                pharmacies.isFirst(),
                pharmacies.isLast()
        );
    }
}
