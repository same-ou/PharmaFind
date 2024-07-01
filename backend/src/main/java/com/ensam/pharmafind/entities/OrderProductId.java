package com.ensam.pharmafind.entities;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Setter;

@Data
@Embeddable
@Setter
public class OrderProductId {
    private Integer orderId;
    private Integer productId;
}
