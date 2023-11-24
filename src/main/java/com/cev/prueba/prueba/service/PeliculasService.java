package com.cev.prueba.prueba.service;

import com.cev.prueba.prueba.domain.Pelicula;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PeliculasService {
	
	final List<Pelicula> peliculas = new ArrayList<>();

	public PeliculasService() {
		Pelicula pelicula = new Pelicula();
		pelicula.setTitulo("Kill Bill");
		pelicula.setPuntuacion(10);
		pelicula.setSinopsis("Uma Thurman con mono amarillo y pegando sablazos. Para qué quieres más.");
		pelicula.setFechaEstreno(new GregorianCalendar(2003, Calendar.JANUARY, 1).getTime());
		pelicula.setDirector("Quentin Tarantino");
		peliculas.add(pelicula);
	}	
	
	public Pelicula getPelicula(int id) {
		return peliculas.get(id-1);
	}
	
	public int add(Pelicula pelicula) {
		peliculas.add(pelicula);
		return peliculas.size();
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}
	
	public void guarda(int id, Pelicula pelicula ) {
		peliculas.set(id-1, pelicula);
	}
	
	public void borra(int id) {
		peliculas.remove(id-1);
	}
	
	public  List<Pelicula> buscaPorTitulo(String titulo) {
		
		List<Pelicula> peliculasResultado = new ArrayList<>();
		for(Pelicula pelicula: peliculas) {
			if( pelicula.getTitulo().contains(titulo)) {
				peliculasResultado.add(pelicula);
			}
		}
		return peliculasResultado;	
	}

}
