package com.ensam.pharmafind.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ImageDTO {
    private String imageUrl;
}
