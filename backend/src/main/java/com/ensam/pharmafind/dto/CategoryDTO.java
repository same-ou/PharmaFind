package com.ensam.pharmafind.dto;

import lombok.*;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class CategoryDTO {
    private Integer id;
    private String name;
}
