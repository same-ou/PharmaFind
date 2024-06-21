package com.ensam.pharmafind.controllers;

import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.service.MinioService;
import com.ensam.pharmafind.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final MinioService minioService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getProducts(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size", required = false) int size
    ){
        return ResponseEntity.ok(productService.getProducts(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @RequestParam(name = "id") Integer id
    ) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(
            @RequestBody ProductRequest productRequest
            ) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest
    ) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadProductImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = minioService.uploadFile(file);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

}
