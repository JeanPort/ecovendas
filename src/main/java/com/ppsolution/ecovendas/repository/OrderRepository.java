package com.ppsolution.ecovendas.repository;

import com.ppsolution.ecovendas.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
