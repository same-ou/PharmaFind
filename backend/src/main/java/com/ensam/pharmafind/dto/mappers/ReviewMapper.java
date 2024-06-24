package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.requests.ReviewRequest;
import com.ensam.pharmafind.dto.responses.ReviewResponse;
import com.ensam.pharmafind.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    Review toReview(ReviewRequest reviewRequest);
    ReviewResponse toReviewResponse(Review review);
}
