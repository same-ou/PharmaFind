package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.OrderProduct;
import com.ensam.pharmafind.entities.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
