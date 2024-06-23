package com.ensam.pharmafind.service;

import com.ensam.pharmafind.dto.requests.ReviewRequest;
import com.ensam.pharmafind.dto.responses.PageResponse;
import com.ensam.pharmafind.dto.responses.ReviewResponse;
import com.ensam.pharmafind.entities.Review;
import com.ensam.pharmafind.dto.mappers.ReviewMapper;
import com.ensam.pharmafind.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // save a review
    public void saveReview(ReviewRequest reviewRequest){
        Review review = ReviewMapper.INSTANCE.toReview(reviewRequest);
        reviewRepository.save(review);
    }

    // get a review by id
    public Review getReview(Integer id){
        return reviewRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Review not found"));
    }

    // delete a review by id
    public void deleteReview(Integer id){
        reviewRepository.deleteById(id);
    }

    // update a review
    public void updateReview(Integer id, ReviewRequest reviewRequest){
        Review review = reviewRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Review not found"));
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        reviewRepository.save(review);
    }

    public PageResponse<ReviewResponse> getReviews(
            Integer pharmacyId,
            int page,
            int size
    ){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<Review> reviews = reviewRepository.findByPharmacyId(pharmacyId, pageable);
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(ReviewMapper.INSTANCE::toReviewResponse)
                .toList();
        return new PageResponse<>(
                reviewResponses,
                reviews.getNumber(),
                reviews.getSize(),
                reviews.getTotalElements(),
                reviews.getTotalPages(),
                reviews.isFirst(),
                reviews.isLast()
        );
    }
}
