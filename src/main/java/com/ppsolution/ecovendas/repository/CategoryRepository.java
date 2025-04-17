package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
