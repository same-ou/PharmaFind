package com.ensam.pharmafind.controllers;


import com.ensam.pharmafind.dto.ProductCreateDTO;
import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.service.PharmacyService;
import com.ensam.pharmafind.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("pharmacies")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<PharmacyResponse>> getPharmacies(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size", required = false) int size
    ){
        return ResponseEntity.ok(pharmacyService.getPharmacies(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacyResponse> getPharmacy(
            @PathVariable Integer id){
        return ResponseEntity.ok(pharmacyService.getPharmacy(id));
    }

    @PostMapping
    public ResponseEntity<PharmacyResponse> savePharmacy(
            @RequestBody @Valid  PharmacyRequest pharmacyRequest,
            Authentication authentication
            ){
        return ResponseEntity.ok(pharmacyService.savePharmacy(pharmacyRequest, authentication));
    }

    @GetMapping("{id}/products")
    public ResponseEntity<PageResponse<ProductResponse>> getProductsByPharmacy(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", name = "size", required = false) int size
    ){
        return ResponseEntity.ok(productService.getProductsByPharmacy(id, page, size));
    }

}
