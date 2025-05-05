package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.request.AddressRequest;
import com.ppsolution.ecovendas.dto.response.AddressResponse;
import com.ppsolution.ecovendas.model.Address;

public interface AddressMapper {

    Address toAddress(AddressRequest addressRequest);
    AddressResponse toAddressResponse(Address address);
}
