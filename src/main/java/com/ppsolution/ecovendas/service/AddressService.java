package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.AddressRequest;
import com.ppsolution.ecovendas.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    List<AddressResponse> findAllAddress();
    AddressResponse findAddressById(Long id);
    AddressResponse updateAddress(AddressRequest addressRequest, Long id);
    void deleteAddress(Long id);
    AddressResponse insertAddress(AddressRequest addressRequest);
}
