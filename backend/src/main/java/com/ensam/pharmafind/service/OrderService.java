package com.ensam.pharmafind.service;

import com.ensam.pharmafind.dto.OrderProductDTO;
import com.ensam.pharmafind.dto.requests.OrderCreateDTO;
import com.ensam.pharmafind.dto.responses.OrderResponseDTO;
import com.ensam.pharmafind.entities.Order;
import com.ensam.pharmafind.entities.OrderProduct;
import com.ensam.pharmafind.entities.PharmacyProduct;
import com.ensam.pharmafind.entities.User;
import com.ensam.pharmafind.enums.OrderStatus;
import com.ensam.pharmafind.repository.OrderProductRepository;
import com.ensam.pharmafind.repository.OrderRepository;
import com.ensam.pharmafind.repository.PharmacyProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PharmacyProductRepository pharmacyProductRepository;

    public OrderResponseDTO placeOrder(Authentication authentication, OrderCreateDTO orderCreateDTO) {
        User costumer = (User) authentication.getPrincipal();

        List<OrderProduct> orderItems = orderCreateDTO.getProducts().stream().map(
                orderProductDTO -> {
                    PharmacyProduct pharmacyProduct = pharmacyProductRepository.findByPharmacyIdAndProductId(
                            orderProductDTO.getPharmacyId(),
                            orderProductDTO.getProductId()
                    );
                   if (pharmacyProduct.getQuantity() < orderProductDTO.getQuantity()) {
                       throw new RuntimeException("Not enough quantity");
                   }
                   pharmacyProduct.setQuantity(pharmacyProduct.getQuantity() - orderProductDTO.getQuantity());
                   pharmacyProductRepository.save(pharmacyProduct);

                   OrderProduct orderProduct = OrderProduct.builder()
                           .product(pharmacyProduct.getProduct())
                           .pharmacy(pharmacyProduct.getPharmacy())
                           .quantity(orderProductDTO.getQuantity())
                           .price(pharmacyProduct.getPrice())
                           .build();
                   return orderProduct;
                }
        ).toList();

        Order order = new Order();
        order.setCustomer(costumer);
        order.setStatus(OrderStatus.PENDING);
        order.setProducts(orderItems);

        orderItems.forEach(orderProduct -> orderProduct.setOrder(order));
        orderRepository.save(order);
        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .build();
    }
}
