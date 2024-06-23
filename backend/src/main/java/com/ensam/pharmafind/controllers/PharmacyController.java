package com.ensam.pharmafind.controllers;


import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.service.PharmacyService;
import com.ensam.pharmafind.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pharmacies")
@RequiredArgsConstructor
public class PharmacyController {
    private final PharmacyService pharmacyService;
    private final ProductService productService;



    @PostMapping
    public ResponseEntity<PharmacyResponse> savePharmacy(
            @RequestBody @Valid  PharmacyRequest pharmacyRequest,
            Authentication authentication
            ){
        return ResponseEntity.ok(pharmacyService.savePharmacy(pharmacyRequest, authentication));
    }


}
