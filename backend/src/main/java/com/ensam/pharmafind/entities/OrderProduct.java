package com.ensam.pharmafind.entities;

import jakarta.persistence.*;

@Entity
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;
    private int quantity;
    private Double price;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
}
