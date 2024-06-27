package com.ensam.pharmafind.dto.requests;

import com.ensam.pharmafind.dto.OrderProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class OrderCreateDTO {
    List<OrderProductDTO> products;
}
