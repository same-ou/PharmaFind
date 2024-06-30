package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.AddressDTO;
import com.ensam.pharmafind.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    AddressDTO toAddressDTO(Address address);
    Address toAddress(AddressDTO addressDTO);
}
