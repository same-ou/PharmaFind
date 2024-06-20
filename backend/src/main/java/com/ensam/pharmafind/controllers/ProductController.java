package com.ensam.pharmafind.controllers;

import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

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

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
