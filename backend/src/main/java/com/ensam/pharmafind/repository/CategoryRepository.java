package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}