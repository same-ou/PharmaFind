package com.ensam.pharmafind.controllers;

import com.ensam.pharmafind.dto.ImageDTO;
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

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }


    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(
            @RequestPart("product") ProductRequest productRequest,
            @RequestPart("images") MultipartFile[] files
    ) {
        if (files.length > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<ImageDTO> images = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    String imageUrl = minioService.uploadFile(file);
                    images.add(new ImageDTO(imageUrl));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            }
        }
        productRequest.setImages(images);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRequest));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ProductResponse> updateProduct(
            @RequestBody ProductRequest productRequest
    ) {
        return ResponseEntity.ok(productService.saveProduct(productRequest));
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
