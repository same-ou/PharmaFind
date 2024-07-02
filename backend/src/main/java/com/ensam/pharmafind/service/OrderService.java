package com.ensam.pharmafind.service;

import com.ensam.pharmafind.dto.mappers.OrderMapper;
import com.ensam.pharmafind.dto.requests.OrderCreateDTO;
import com.ensam.pharmafind.dto.responses.OrderResponseDTO;
import com.ensam.pharmafind.entities.*;
import com.ensam.pharmafind.enums.OrderStatus;
import com.ensam.pharmafind.repository.AddressRepository;
import com.ensam.pharmafind.repository.OrderProductRepository;
import com.ensam.pharmafind.repository.OrderRepository;
import com.ensam.pharmafind.repository.PharmacyProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PharmacyProductRepository pharmacyProductRepository;
    private final OrderProductRepository orderProductRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public OrderResponseDTO placeOrder(Authentication authentication, OrderCreateDTO orderCreateDTO) {
        User customer = (User) authentication.getPrincipal();

        Address address = OrderMapper.INSTANCE.toAddress(orderCreateDTO.getAddress());
        address = addressRepository.save(address);

        // Create a new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        order.setAddress(address);

        // Save the order to get an ID assigned
        order = orderRepository.save(order);

        Order finalOrder = order;
        List<OrderProduct> orderItems = orderCreateDTO.getProducts().stream().map(orderProductDTO -> {
            PharmacyProduct pharmacyProduct = pharmacyProductRepository.findByPharmacyIdAndProductId(
                    orderProductDTO.getPharmacyId(),
                    orderProductDTO.getProductId()
            );

           if (pharmacyProduct.getQuantity() < orderProductDTO.getQuantity()) {
                throw new RuntimeException("Not enough quantity");
            }

            pharmacyProduct.setQuantity(pharmacyProduct.getQuantity() - orderProductDTO.getQuantity());
            pharmacyProductRepository.save(pharmacyProduct);

            // Create and set OrderProductId
            OrderProductId orderProductId = new OrderProductId();
            orderProductId.setOrderId(finalOrder.getId());
            orderProductId.setProductId(pharmacyProduct.getProduct().getId());

            // Create OrderProduct
            OrderProduct orderProduct = OrderProduct.builder()
                    .id(orderProductId)
                    .order(finalOrder)
                    .product(pharmacyProduct.getProduct())
                    .pharmacy(pharmacyProduct.getPharmacy())
                    .quantity(orderProductDTO.getQuantity())
                    .price(pharmacyProduct.getPrice())
                    .build();

            return orderProduct;
        }).collect(Collectors.toList());

        // Set order products and total
        order.setProducts(orderItems);
        order.setTotal(orderItems.stream().mapToDouble(op -> op.getPrice() * op.getQuantity()).sum());

        // Save all order products
        orderProductRepository.saveAll(orderItems);

        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.getStatus())
                .build();
    }
}
