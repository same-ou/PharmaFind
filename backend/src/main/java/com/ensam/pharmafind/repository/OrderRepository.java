package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
