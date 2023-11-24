package com.cev.prueba.prueba.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	String titulo;
	int puntuacion;
	String sinopsis;
	String director;
	Date fechaestreno;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "cine_pelicula",
			joinColumns = @JoinColumn(name = "pelicula_id"),
			inverseJoinColumns = @JoinColumn(name = "cine_id"))
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIgnoreProperties("peliculas")
	private List<Cine> cines;

	@OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Review> reviews = new ArrayList<>();


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirector() {
		return director;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaestreno = fechaEstreno;
	}
	public Date getFechaEstreno() {
		return fechaestreno;
	}
	public void setCines(List<Cine> cines) {
		this.cines = cines;
	}
	public List<Cine> getCines() {
		return cines;
	}

	@Override
	public String toString() {
		return "Pelicula [" +
				"id=" + id +
				", titulo=" + titulo +
				", puntuacion=" + puntuacion +
				", sinopsis=" + sinopsis +
				", director=" + director +
				", fechaestreno=" + fechaestreno +
				", cines=" + cines +
				", reviews=" + reviews + "]";
	}

}
