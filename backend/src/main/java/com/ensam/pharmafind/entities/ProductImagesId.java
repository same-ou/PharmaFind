package com.ensam.pharmafind.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Embeddable
public class ProductImagesId {
    private Integer productId;
}
