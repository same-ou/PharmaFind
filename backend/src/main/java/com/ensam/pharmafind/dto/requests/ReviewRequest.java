package com.ensam.pharmafind.dto.requests;

import lombok.Data;

@Data

public class ReviewRequest {
    private Integer id;
    private Integer clientId;
    private Integer pharmacyId;
    private int rating;
    private String comment;
}
