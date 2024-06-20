package com.ensam.pharmafind.controllers;

import com.ensam.pharmafind.service.AuthenticationService;
import com.ensam.pharmafind.dto.responses.AuthenticationResponse;
import com.ensam.pharmafind.dto.requests.LoginRequest;
import com.ensam.pharmafind.dto.requests.RegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @GetMapping("/activate")
    public ResponseEntity<AuthenticationResponse> activate(
            @RequestParam String token) throws MessagingException {
        return ResponseEntity.ok(authenticationService.activate(token));
    }
}
