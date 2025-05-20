package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.Address;
import com.ppsolution.ecovendas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUser(User user);
    Optional<Address> findByUserAndId(User user, Long id);
}
