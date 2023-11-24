package com.cev.prueba.prueba.repositories;

import com.cev.prueba.prueba.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long>
{ List<Review> findByOrderByPuntuacionDesc();}
