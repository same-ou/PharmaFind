package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId")
    Page<Product> findAllByCategoryId(@Param("categoryId") Integer categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId AND p.name LIKE %:name%")
    Page<Product> findAllByCategoryIdAndNameContaining(@Param("categoryId") Integer categoryId, @Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:searchQuery%")
    Page<Product> searchProducts(String searchQuery, Pageable pageable);
}
