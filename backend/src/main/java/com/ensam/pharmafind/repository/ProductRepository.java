package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}
