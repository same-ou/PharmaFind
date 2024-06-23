package com.ensam.pharmafind.dto.mappers;


import com.ensam.pharmafind.dto.AddressDTO;
import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.entities.Address;
import com.ensam.pharmafind.entities.Pharmacy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PharmacyMapper {
  PharmacyMapper INSTANCE = Mappers.getMapper(PharmacyMapper.class);

    AddressDTO toAddressDTO(Address address);

    Address toAddress(AddressDTO addressDTO);

    Pharmacy toPharmacy(PharmacyRequest pharmacyRequest);
    PharmacyResponse toPharmacyResponse(Pharmacy pharmacy);
}
