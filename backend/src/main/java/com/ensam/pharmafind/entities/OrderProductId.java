package com.ensam.pharmafind.entities;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderProductId {
    private Integer orderId;
    private Integer productId;
}
