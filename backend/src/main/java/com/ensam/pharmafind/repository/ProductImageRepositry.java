package com.ensam.pharmafind.repository;

import com.ensam.pharmafind.entities.ProductImages;
import com.ensam.pharmafind.entities.ProductImagesId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepositry extends JpaRepository<ProductImages, ProductImagesId>{

}
