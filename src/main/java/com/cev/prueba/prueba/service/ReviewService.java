package com.cev.prueba.prueba.service;

import com.cev.prueba.prueba.domain.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    final List<Review> reviews = new ArrayList<>();

    public ReviewService() {
        Review review = new Review();
        review.setReview("Buena");
        reviews.add(review);
    }

    public Review getReview(int id) {
        return reviews.get(id-1);
    }

    public int addReview(Review review) {
        reviews.add(review);
        return reviews.size();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void guardaReview(int id, Review review ) {
        reviews.set(id-1, review);
    }

    public void borraReview(int id) {
        reviews.remove(id-1);
    }

    public List<Review> buscaPorReview(String review) {

        List<Review> reviewsResultado = new ArrayList<>();
        for (Review review1 : reviews) {
            if (review1.getReview().contains(review)) {
                reviewsResultado.add(review1);
            }
        }
        return reviewsResultado;
    }
}
