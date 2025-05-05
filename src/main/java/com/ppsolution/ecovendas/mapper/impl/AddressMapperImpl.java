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

        return Address.builder()
                .city(addressRequest.city())
                .state(addressRequest.state())
                .number(addressRequest.number())
                .complement(addressRequest.complement())
                .isDefault(addressRequest.isDefault() == null ? 1: addressRequest.isDefault())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Override
    public AddressResponse toAddressResponse(Address address) {
        if (address == null) return null;
        return new AddressResponse(
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
