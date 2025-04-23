package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
