package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
