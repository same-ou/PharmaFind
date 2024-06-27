package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.ImageDTO;
import com.ensam.pharmafind.dto.requests.ProductRequest;
import com.ensam.pharmafind.dto.responses.ProductResponse;
import com.ensam.pharmafind.entities.Image;
import com.ensam.pharmafind.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapperImp {

    public ImageDTO toImageDto(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDTO.ImageDTOBuilder imageDTO = ImageDTO.builder();

        imageDTO.imageUrl( image.getImageUrl() );

        return imageDTO.build();
    }


    public Image toImage(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        Image.ImageBuilder image = Image.builder();

        image.imageUrl( imageDTO.getImageUrl() );

        return image.build();
    }

    public Product toProduct(ProductRequest productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productRequest.getName() );
        product.description( productRequest.getDescription() );
        product.price( productRequest.getPrice() );
        product.images( imageDTOListToImageList( productRequest.getImages() ) );

        return product.build();
    }

    public ProductResponse toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setDescription( product.getDescription() );
        productResponse.setPrice( product.getPrice() );
        productResponse.setImages( imageListToImageDTOList( product.getImages() ) );

        return productResponse;
    }

    protected List<Image> imageDTOListToImageList(List<ImageDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Image> list1 = new ArrayList<Image>( list.size() );
        for ( ImageDTO imageDTO : list ) {
            list1.add( toImage( imageDTO ) );
        }

        return list1;
    }

    protected List<ImageDTO> imageListToImageDTOList(List<Image> list) {
        if ( list == null ) {
            return null;
        }

        List<ImageDTO> list1 = new ArrayList<ImageDTO>( list.size() );
        for ( Image image : list ) {
            list1.add( toImageDto( image ) );
        }

        return list1;
    }
}
