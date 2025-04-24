package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"category"})
    Optional<Product> findById(Long id);

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
}
