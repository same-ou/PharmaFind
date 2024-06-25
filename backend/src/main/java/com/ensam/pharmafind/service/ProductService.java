package com.ensam.pharmafind.service;


import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.ProductResponse;
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

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final PharmacyProductRepository pharmacyProductRepository;

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

    public ProductResponse getProduct(Integer id) {
        return productRepository.findById(id)
                .map(ProductMapper.INSTANCE::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        Product product = ProductMapper.INSTANCE.toProduct(productRequest);
        Product product1 = productRepository.save(product);
        return ProductMapper.INSTANCE.toProductResponse(product1);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
