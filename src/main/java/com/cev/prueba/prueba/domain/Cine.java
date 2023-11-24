package com.cev.prueba.prueba.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String nombre;
    String poblacion;
    @Column(name = "codigo_postal")
    int codigoPostal;
    String provincia;
    double precio;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "cine_pelicula",
            joinColumns = @JoinColumn(name = "cine_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    @JsonIgnoreProperties("cines")
    private List<Pelicula> peliculas;


    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
    public String getPoblacion() {
        return poblacion;
    }
    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public int getCodigoPostal() {
        return codigoPostal;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPeliculas(Pelicula pelicula) {
        this.peliculas.add(pelicula);
    }
    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    @Override
    public String toString() {
        return "Cine[" +
                "id=" + id +
                ", nombre='" + nombre +
                ", poblacion='" + poblacion +
                ", codigoPostal=" + codigoPostal +
                ", provincia='" + provincia +
                ", precio=" + precio +
                ", peliculas=" + peliculas + "]";
    }
}
