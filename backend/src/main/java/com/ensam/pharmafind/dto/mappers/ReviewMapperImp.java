package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.requests.ReviewRequest;
import com.ensam.pharmafind.dto.responses.ReviewResponse;
import com.ensam.pharmafind.entities.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapperImp {

    public Review toReview(ReviewRequest reviewRequest) {
        if ( reviewRequest == null ) {
            return null;
        }

        Review.ReviewBuilder review = Review.builder();

        review.id( reviewRequest.getId() );
        review.rating( reviewRequest.getRating() );
        review.comment( reviewRequest.getComment() );

        return review.build();
    }

    public ReviewResponse toReviewResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewResponse reviewResponse = new ReviewResponse();

        reviewResponse.setId( review.getId() );
        reviewResponse.setComment( review.getComment() );

        return reviewResponse;
    }
}
