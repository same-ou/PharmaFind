package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.CategoryDTO;
import com.ensam.pharmafind.dto.ImageDTO;
import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.entities.Category;
import com.ensam.pharmafind.entities.Image;
import com.ensam.pharmafind.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ImageDTO toImageDto(Image image);
    Image toImage(ImageDTO imageDTO);

    Product toProduct(ProductRequest productRequest);
    ProductResponse toProductResponse(Product product);
}
