package com.ensam.pharmafind.controllers;

import com.ensam.pharmafind.dto.requests.OrderCreateDTO;
import com.ensam.pharmafind.dto.responses.OrderResponseDTO;
import com.ensam.pharmafind.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder(Authentication authentication, OrderCreateDTO orderCreateDTO) {
       return ResponseEntity.ok(orderService.placeOrder(authentication, orderCreateDTO));
    }
}
