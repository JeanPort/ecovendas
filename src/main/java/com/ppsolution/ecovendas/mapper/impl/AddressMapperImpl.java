package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.request.AddressRequest;
import com.ppsolution.ecovendas.dto.response.AddressResponse;
import com.ppsolution.ecovendas.mapper.AddressMapper;
import com.ppsolution.ecovendas.model.Address;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AddressMapperImpl implements AddressMapper {


    @Override
    public Address toAddress(AddressRequest addressRequest) {
        if (addressRequest == null) return null;

        var addres = new Address();
        addres.setCity(addressRequest.city());
        addres.setState(addressRequest.state());
        addres.setNumber(addressRequest.number());
        addres.setComplement(addressRequest.complement());
        addres.setIsDefault(addressRequest.isDefault() == null ? 1: addressRequest.isDefault());
        addres.setCreatedAt(LocalDateTime.now());
        addres.setUpdatedAt(LocalDateTime.now());
        addres.setZipCode(addressRequest.zipCode());
        addres.setStreet(addressRequest.street());
        return addres;
    }

    @Override
    public AddressResponse toAddressResponse(Address address) {
        if (address == null) return null;
        return new AddressResponse(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getIsDefault(),
                address.getCreatedAt(),
                address.getUpdatedAt()
        );
    }
}
