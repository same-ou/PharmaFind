package com.ensam.pharmafind.dto.responses;

import com.ensam.pharmafind.enums.OrderStatus;
import lombok.Builder;

@Builder
public class OrderResponseDTO {
    private Integer id;
    private OrderStatus status;
}
