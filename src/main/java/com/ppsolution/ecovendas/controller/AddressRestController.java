package com.ppsolution.ecovendas.controller;

import com.ppsolution.ecovendas.dto.request.AddressRequest;
import com.ppsolution.ecovendas.dto.response.AddressResponse;
import com.ppsolution.ecovendas.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressRestController {

    private final AddressService service;

    public AddressRestController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> findAllAddress(){
        var allAddress = service.findAllAddress();
        return ResponseEntity.ok(allAddress);
    }

    @GetMapping("/{idAddress}")
    public ResponseEntity<AddressResponse> findAddresById(@PathVariable(name = "idAddress") Long idAddress){
        var address = service.findAddressById(idAddress);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{idAddress}")
    public ResponseEntity<AddressResponse> updateAddress(@PathVariable(name = "idAddress") Long idAddress, @Valid @RequestBody AddressRequest request) {
        var address = service.updateAddress(request, idAddress);
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<AddressResponse> insertAddress(@Valid @RequestBody AddressRequest request){
        var address = service.insertAddress(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @DeleteMapping("/{idAddress}")
    public ResponseEntity<Void> deleteAddress(@PathVariable(name = "idAddress") Long idAddress){
        service.deleteAddress(idAddress);
        return ResponseEntity.noContent().build();
    }
}
