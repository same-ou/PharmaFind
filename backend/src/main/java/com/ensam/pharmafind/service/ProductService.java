package com.ensam.pharmafind.service;


import com.ensam.pharmafind.dto.ImageDTO;
import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.entities.*;
import com.ensam.pharmafind.dto.mappers.ProductMapper;
import com.ensam.pharmafind.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final PharmacyProductRepository pharmacyProductRepository;
    private final MinioService minioService;
    private final CategoryRepository categoryRepository;
    private final PharmacyRepository pharmacyRepository;
    private final UserRepository userRepository;

    public PageResponse<ProductResponse> getProducts(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        // map images to their URLs
        for (ProductResponse productResponse : productResponses) {
            List<ImageDTO> images = productResponse.getImages().stream().map(image -> {
                try {
                    String imageUrl = minioService.getFileUrl(image.getImageUrl());
                    return new ImageDTO(imageUrl);
                } catch (Exception e) {
                    // Handle exception appropriately
                    return new ImageDTO(image.getImageUrl()); // Return the filename as a fallback
                }
            }).collect(Collectors.toList());
            productResponse.setImages(images);
        }
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

    public PageResponse<ProductResponse> getProductsByPharmacy(Authentication authentication, int page, int size) {
        User user = userRepository.findById(((User) authentication.getPrincipal()).getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!user.getRole().getName().equals("PHARMACIST")) {
            throw new EntityNotFoundException("User is not a pharmacist");
        }
        if(user.getPharmacy() == null) {
            throw new EntityNotFoundException("User does not have a pharmacy");
        }
        Integer pharmacyId = user.getPharmacy().getId();
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PharmacyProduct> pharmacyProducts = pharmacyProductRepository
                .findByPharmacyId(pharmacyId, pageable);
        List<Product> products = pharmacyProducts.stream()
                .map(PharmacyProduct::getProduct)
                .toList();
        List<ProductResponse> productResponses = products.stream()
                .map(ProductMapper.INSTANCE::toProductResponse)
                .toList();
        // map images to their URLs
        for (ProductResponse productResponse : productResponses) {
            List<ImageDTO> images = productResponse.getImages().stream().map(image -> {
                try {
                    String imageUrl = minioService.getFileUrl(image.getImageUrl());
                    return new ImageDTO(imageUrl);
                } catch (Exception e) {
                    // Handle exception appropriately
                    return new ImageDTO(image.getImageUrl()); // Return the filename as a fallback
                }
            }).collect(Collectors.toList());
            productResponse.setImages(images);
        }
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

    @Transactional
    public ProductResponse saveProduct(ProductRequest productRequest) {
        // Convert ProductRequest to Product entity
        Product product = ProductMapper.INSTANCE.toProduct(productRequest);

//        if(product.getImages () == null || product.getImages().isEmpty()){
//            throw new EntityNotFoundException("you must select at least one image");
//        }

        for (Image image : product.getImages()) {
            image.setProduct(product);
        }

        if (productRequest.getCategories() != null && !productRequest.getCategories().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(productRequest.getCategories());

//            if(categories.isEmpty()){
//                throw new EntityNotFoundException("Categories not found");
//            }

            for (Category category : categories) {
                category.getProducts().add(product);
            }

            product.setCategories(categories);
        }
//        else {
//            throw new EntityNotFoundException("you must select at least one category");
//        }

        // Set the pharmacy and create the pharmacy product association
        Pharmacy pharmacy = pharmacyRepository.findById(productRequest.getPharmacyId())
                .orElseThrow(() -> new EntityNotFoundException("Pharmacy not found"));

        PharmacyProduct pharmacyProduct = new PharmacyProduct();
        PharmacyProductId pharmacyProductId = new PharmacyProductId(product.getId(), pharmacy.getId());
        pharmacyProduct.setId(pharmacyProductId);
        pharmacyProduct.setPharmacy(pharmacy);
        pharmacyProduct.setProduct(product);
        pharmacyProduct.setQuantity(productRequest.getQuantity());
        product.setPharmacyProducts(List.of(pharmacyProduct));

        Product savedProduct = productRepository.save(product);

        return ProductMapper.INSTANCE.toProductResponse(savedProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
