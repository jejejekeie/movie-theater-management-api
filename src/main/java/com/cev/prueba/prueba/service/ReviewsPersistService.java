package com.cev.prueba.prueba.service;

import com.cev.prueba.prueba.domain.Pelicula;
import com.cev.prueba.prueba.domain.Review;
import com.cev.prueba.prueba.repositories.PeliculasRepository;
import com.cev.prueba.prueba.repositories.ReviewsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewsPersistService {

   final
   ReviewsRepository reviewsRepository;
   PeliculasRepository peliculasRepository;

    @Autowired
    public ReviewsPersistService(ReviewsRepository reviewsRepository, PeliculasRepository peliculasRepository) {
        this.reviewsRepository = reviewsRepository;
        this.peliculasRepository = peliculasRepository;
    }
    final List<Review> reviews = new ArrayList<>();
    public Review getReview(Long id) {
        return reviewsRepository.findById(id).orElse(null);
    }
    @Transactional
    public Long addReview(Review review) {
        if (review.getPelicula() == null || review.getPelicula().getId() == null) {
            throw new RuntimeException("Pelicula ID is required");
        }
        Pelicula pelicula = peliculasRepository.findById(review.getPelicula().getId())
                .orElseThrow(() -> new EntityNotFoundException("Pelicula not found with ID: " + review.getPelicula().getId()));
        review.setPelicula(pelicula);
        return reviewsRepository.save(review).getId();
    }

    public List<Review> getReviews() {
        return reviews;
    }
    public void guardaReview(Long id, Review review ) {
        Review reviewGuardado = reviewsRepository.getReferenceById(id);
        reviewGuardado.setReview(review.getReview());
        reviewGuardado.setPuntuacion(review.getPuntuacion());
        reviewsRepository.save(reviewGuardado);
    }
    public void borraReview(Long id) {
        reviewsRepository.deleteById(id);
    }
    public List<Review> getReviewsPorMayorPuntuacion() {
        return reviewsRepository.findByOrderByPuntuacionDesc();
    }
}
