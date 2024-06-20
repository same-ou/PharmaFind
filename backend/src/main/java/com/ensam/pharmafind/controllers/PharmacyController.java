package com.ensam.pharmafind.controllers;


import com.ensam.pharmafind.dto.requests.PharmacyRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.PharmacyResponse;
import com.ensam.pharmafind.service.PharmacyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {
    private final PharmacyService pharmacyService;


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
           @Valid @RequestBody PharmacyRequest pharmacyRequest,
           Authentication authentication
    ){
        return ResponseEntity.ok(pharmacyService.savePharmacy(pharmacyRequest, authentication));
    }
}
