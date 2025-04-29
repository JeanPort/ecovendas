package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
