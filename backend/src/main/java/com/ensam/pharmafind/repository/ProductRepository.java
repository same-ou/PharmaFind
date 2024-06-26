package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findByNameContainingIgnoreCase(String name);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
}
