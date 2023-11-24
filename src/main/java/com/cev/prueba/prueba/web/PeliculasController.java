package com.cev.prueba.prueba.web;

import com.cev.prueba.prueba.domain.Cine;
import com.cev.prueba.prueba.domain.Pelicula;
import com.cev.prueba.prueba.service.CinesPersistService;
import com.cev.prueba.prueba.service.PeliculasPersistService;
import com.cev.prueba.prueba.web.error.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeliculasController {

	final
	PeliculasPersistService peliculasPersistService;
	CinesPersistService cinesPersistService;

	@Autowired
	public PeliculasController(CinesPersistService cinesPersistService, PeliculasPersistService peliculasPersistService) {
		this.cinesPersistService = cinesPersistService;
		this.peliculasPersistService = peliculasPersistService;
	}

	@GetMapping(path = "/api/peliculas/{id}")
	Pelicula getPelicula(@PathVariable Long id) {
		if(peliculasPersistService.getPelicula(id)!=null)
		{
		return peliculasPersistService.getPelicula(id);
		} else {
		throw new CustomError("La pelicula no existe");
		}
	}

	@GetMapping(path = "/api/peliculas")
	List<Pelicula> getPeliculas(@RequestParam(required = false) String titulo, @RequestParam(required = false, name = "puntuacion.min", defaultValue = "2") Long puntuacionMinima ){
		if (puntuacionMinima<2) {
			throw new CustomError("La puntuacion no puede ser menor que 2");
		}
        return peliculasPersistService.getPeliculas();
    }

	@PostMapping(path = "/api/peliculas")
	public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
		peliculasPersistService.addPelicula(pelicula);
		return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
	}


	@PutMapping(path = "/api/peliculas/{id}")
	Pelicula modificaPelicula(@RequestBody Pelicula pelicula,@PathVariable Long id) {
		peliculasPersistService.guardaPelicula(id, pelicula);
		return pelicula;		
	}

	@DeleteMapping(path="/api/peliculas/{id}")
	String borraPelicula(@PathVariable Long id) {
		peliculasPersistService.borraPelicula(id);
		return("OK");
	}
	
	@GetMapping(path = "/peliculasHeader")
	ResponseEntity<List<Pelicula>> getPeliculasHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("MiHeaderRespuesta", "HeaderRespuesta");
		
		return ResponseEntity.ok().headers(headers).body(peliculasPersistService.getPeliculas());
	}

	@DeleteMapping(path = "/api/cine/{id}")
	public ResponseEntity<String> borraCine(@PathVariable Long id) {
		try {
			peliculasPersistService.borraCine(id);
			return ResponseEntity.ok("Cine borrado con éxito");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping(path = "/api/cines/precioAsc")
	List<Cine> getCinesPorPrecioAsc() {
		return cinesPersistService.getCinesPorPrecioAsc();
	}
}

