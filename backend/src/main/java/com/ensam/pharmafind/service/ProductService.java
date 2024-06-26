package com.ensam.pharmafind.service;


import com.ensam.pharmafind.dto.ImageDTO;
import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.entities.Image;
import com.ensam.pharmafind.entities.PharmacyProduct;
import com.ensam.pharmafind.entities.Product;
import com.ensam.pharmafind.dto.mappers.ProductMapper;
import com.ensam.pharmafind.repository.PharmacyProductRepository;
import com.ensam.pharmafind.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final PharmacyProductRepository pharmacyProductRepository;
    private final MinioService minioService;

    public PageResponse<ProductResponse> getProducts(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }

    public PageResponse<ProductResponse> getProductsByCategory(Integer categoryId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Product> products = productRepository.findAllByCategoryId(categoryId, pageable);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }

    public PageResponse<ProductResponse> getProductsByPharmacy(Integer pharmacyId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PharmacyProduct> pharmacyProducts = pharmacyProductRepository
                .findByPharmacyId(pharmacyId, pageable);
        List<Product> products = pharmacyProducts.stream()
                .map(PharmacyProduct::getProduct)
                .toList();
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                pharmacyProducts.getNumber(),
                pharmacyProducts.getSize(),
                pharmacyProducts.getTotalElements(),
                pharmacyProducts.getTotalPages(),
                pharmacyProducts.isFirst(),
                pharmacyProducts.isLast()
        );
    }
  
    public PageResponse<ProductResponse> getProductsByCategoryAndName(Integer categoryId, String name, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Product> products = productRepository.findAllByCategoryIdAndNameContaining(categoryId, name, pageable);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }

<<<<<<< HEAD

=======
    public PageResponse<ProductResponse> getProductsByName(String name, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Product> products = productRepository.findAllByNameContaining(name, pageable);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        return new PageResponse<>(
                productResponses,
                products.getNumber(),
                products.getSize(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.isFirst(),
                products.isLast()
        );
    }
>>>>>>> 597f58068363ef0b2c1928a6d5672319b0eb0291

    public ProductResponse getProduct(Integer id) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductResponse response = ProductMapper.INSTANCE.toProductResponse(product);
                    List<ImageDTO> images = response.getImages().stream().map(image -> {
                        try {
                            String imageUrl = minioService.getFileUrl(image.getImageUrl());
                            return new ImageDTO(imageUrl);
                        } catch (Exception e) {
                            // Handle exception appropriately
                            return new ImageDTO(image.getImageUrl()); // Return the filename as a fallback
                        }
                    }).collect(Collectors.toList());
                    response.setImages(images);
                    return response;
                })
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }



    public ProductResponse saveProduct(ProductRequest productRequest) {
        // Convert ProductRequest to Product entity
        Product product = ProductMapper.INSTANCE.toProduct(productRequest);

        // Set the product for each image
        for (Image image : product.getImages()) {
            image.setProduct(product);
        }

        // Save the product with associated images
        Product savedProduct = productRepository.save(product);

        return ProductMapper.INSTANCE.toProductResponse(savedProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
