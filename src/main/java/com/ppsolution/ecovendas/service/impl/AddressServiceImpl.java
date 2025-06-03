package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.AddressRequest;
import com.ppsolution.ecovendas.dto.response.AddressResponse;
import com.ppsolution.ecovendas.exception.NotFoundException;
import com.ppsolution.ecovendas.mapper.AddressMapper;
import com.ppsolution.ecovendas.model.Address;
import com.ppsolution.ecovendas.repository.AddressRepository;
import com.ppsolution.ecovendas.service.AddressService;
import com.ppsolution.ecovendas.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository repository;

    private final AddressMapper mapper;


    public AddressServiceImpl(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AddressResponse> findAllAddress() {
        var user = SecurityUtil.getUserAuthenticated();
        var addressList = repository.findAllByUser(user);
        return addressList.stream().map(mapper::toAddressResponse).toList();
    }

    @Override
    public AddressResponse findAddressById(Long id) {
        var address = getAddress(id);
        return mapper.toAddressResponse(address);
    }

    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest, Long id) {
        var address = getAddress(id);
        var addressToSave = mapper.toAddress(addressRequest);
        addressToSave.setCreatedAt(address.getCreatedAt());
        addressToSave.setId(address.getId());
        addressToSave.setUser(address.getUser());
        addressToSave = repository.save(addressToSave);
        return mapper.toAddressResponse(addressToSave);
    }

    @Override
    public void deleteAddress(Long id) {
        var address = getAddress(id);
        repository.delete(address);
    }

    @Override
    public AddressResponse insertAddress(AddressRequest addressRequest) {
        var address = mapper.toAddress(addressRequest);
        var user = SecurityUtil.getUserAuthenticated();
        address.setUser(user);
        address = repository.save(address);
        return mapper.toAddressResponse(address);
    }

    private Address getAddress(Long id) {
        var user = SecurityUtil.getUserAuthenticated();
        return repository.findByUserAndId(user, id).orElseThrow(NotFoundException::new);
    }
}
