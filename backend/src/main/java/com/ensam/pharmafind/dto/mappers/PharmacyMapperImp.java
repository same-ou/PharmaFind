package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.AddressDTO;
import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.entities.Address;
import com.ensam.pharmafind.entities.Pharmacy;
import org.springframework.stereotype.Service;

@Service
public class PharmacyMapperImp {

    public AddressDTO toAddressDTO(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO.AddressDTOBuilder addressDTO = AddressDTO.builder();

        addressDTO.street( address.getStreet() );
        addressDTO.city( address.getCity() );
        addressDTO.postalCode( address.getPostalCode() );

        return addressDTO.build();
    }


    public Address toAddress(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.street( addressDTO.getStreet() );
        address.city( addressDTO.getCity() );
        address.postalCode( addressDTO.getPostalCode() );

        return address.build();
    }


    public Pharmacy toPharmacy(PharmacyRequest pharmacyRequest) {
        if ( pharmacyRequest == null ) {
            return null;
        }

        Pharmacy.PharmacyBuilder pharmacy = Pharmacy.builder();

        pharmacy.name( pharmacyRequest.getName() );
        pharmacy.telephone( pharmacyRequest.getTelephone() );
        pharmacy.address( toAddress( pharmacyRequest.getAddress() ) );

        return pharmacy.build();
    }


    public PharmacyResponse toPharmacyResponse(Pharmacy pharmacy) {
        if ( pharmacy == null ) {
            return null;
        }

        PharmacyResponse pharmacyResponse = new PharmacyResponse();

        pharmacyResponse.setName( pharmacy.getName() );
        pharmacyResponse.setTelephone( pharmacy.getTelephone() );
        pharmacyResponse.setAddress( toAddressDTO( pharmacy.getAddress() ) );

        return pharmacyResponse;
    }
}
