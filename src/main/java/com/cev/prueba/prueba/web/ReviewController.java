package com.cev.prueba.prueba.web;

import com.cev.prueba.prueba.domain.Review;
import com.cev.prueba.prueba.service.ReviewsPersistService;
import com.cev.prueba.prueba.web.error.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    final
    ReviewsPersistService reviewsPersistService;

    public ReviewController(ReviewsPersistService reviewsPersistService) {
        this.reviewsPersistService = reviewsPersistService;
    }

    @GetMapping(path = "/api/reviews/{id}")
    Review getReview(@PathVariable Long id) {
        if (reviewsPersistService.getReview(id) == null) {
            throw new CustomError("No existe la review con id " + id);
        }
        return reviewsPersistService.getReview(id);
    }

    @GetMapping(path = "/api/reviews")
    public List<Review> getReviews(@RequestParam(required = false) String review) {
        return reviewsPersistService.getReviews();
    }

    @PostMapping(path = "/api/reviews")
    public Long addReview(@RequestBody Review review) {
        return reviewsPersistService.addReview(review);
    }

    @PutMapping(path = "/api/reviews/{id}")
    Review changeReview(@RequestBody Review review, @PathVariable Long id) {
        reviewsPersistService.guardaReview(id, review);
        return review;
    }

    @DeleteMapping(path = "/api/reviews/{id}")
    String deleteReview(@PathVariable Long id) {
        reviewsPersistService.borraReview(id);
        return("OK");
    }

    @GetMapping(path = "/reviewHeader")
    ResponseEntity<List<Review>> getReviewsHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("MiHeaderRespuesta", "HeaderRespuesta");

        return ResponseEntity.ok().headers(headers).body(reviewsPersistService.getReviews());
    }

    @GetMapping(path = "/api/reviews/mayorPuntuacion")
    List<Review> getReviewsPorMayorPuntuacion() {
        return reviewsPersistService.getReviewsPorMayorPuntuacion();
    }
}
