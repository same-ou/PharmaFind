package com.ensam.pharmafind.mappers;


import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.entities.Pharmacy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PharmacyMapper {
  PharmacyMapper INSTANCE = Mappers.getMapper(PharmacyMapper.class);
    Pharmacy toPharmacy(PharmacyRequest pharmacyRequest);
    PharmacyResponse toPharmacyResponse(Pharmacy pharmacy);
}
