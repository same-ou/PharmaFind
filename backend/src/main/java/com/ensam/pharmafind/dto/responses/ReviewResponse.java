package com.ensam.pharmafind.dto.responses;

import lombok.Data;

@Data
public class ReviewResponse {
    private Integer id;
    private Integer clientId;
    private Integer pharmacyId;
    private int evaluation;
    private String comment;
}
