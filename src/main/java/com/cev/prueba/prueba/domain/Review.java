package com.cev.prueba.prueba.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true)
    String review;
    Integer puntuacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pelicula_id")
    @JsonIgnoreProperties("reviews")
    private Pelicula pelicula;

    public Review() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setReview(String comment) {
        this.review =comment;
    }
    public String getReview() {
        return review;
    }
    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
    public Integer getPuntuacion() {
        return puntuacion;
    }
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
    public Pelicula getPelicula() {
        return pelicula;
    }


    @Override
    public String toString() {
        return "Review[" +
                "id=" + id +
                ", comment='" + review +
                ", puntuacion='" + puntuacion +"]";
    }
}
