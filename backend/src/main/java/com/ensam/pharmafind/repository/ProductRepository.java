package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);
    Page<Product> findAllByCategoryIdAndNameContaining(Integer categoryId, String name, Pageable pageable);
}
