package com.cev.prueba.prueba.service;

import com.cev.prueba.prueba.domain.Cine;
import com.cev.prueba.prueba.domain.Pelicula;
import com.cev.prueba.prueba.repositories.CinesRepository;
import com.cev.prueba.prueba.repositories.PeliculasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PeliculasPersistService {
    private final PeliculasRepository peliculasRepository;
    private final CinesRepository cinesRepository;

    @Autowired
    public PeliculasPersistService(PeliculasRepository peliculasRepository, CinesRepository cinesRepository) {
        this.peliculasRepository = peliculasRepository;
        this.cinesRepository = cinesRepository;
    }

    List<Pelicula> peliculas = new ArrayList<>();

    public Pelicula getPelicula(Long id) {
        return peliculasRepository.findById(id).orElse(null);
    }

    public void addPelicula(Pelicula pelicula) {
        List<Cine> attachedCines = new ArrayList<>();
        if (pelicula.getCines() != null) {
            for (Cine cine : pelicula.getCines()) {
                cinesRepository.findById(cine.getId()).ifPresent(attachedCines::add);
            }
            pelicula.setCines(attachedCines);
        }
        peliculasRepository.save(pelicula);
    }

    public void guardaPelicula(Long id, Pelicula pelicula) {
        Pelicula peliculaGuardada = peliculasRepository.getReferenceById(id);
        peliculaGuardada.setTitulo(pelicula.getTitulo());
        peliculaGuardada.setPuntuacion(pelicula.getPuntuacion());
        peliculaGuardada.setSinopsis(pelicula.getSinopsis());
        peliculaGuardada.setDirector(pelicula.getDirector());
        peliculaGuardada.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaGuardada.setCines(pelicula.getCines());
        peliculasRepository.save(peliculaGuardada);
    }

    public List<Pelicula> getPeliculas() {
        return peliculasRepository.findAll();
    }

    public void borraPelicula(Long id) {
        peliculasRepository.deleteById(id);
    }

    @Transactional
    public void addCine(Long peliculaId, Long cineId) {
        Optional<Pelicula> peliculaOptional = peliculasRepository.findById(peliculaId);
        Optional<Cine> cineOptional = cinesRepository.findById(cineId);

        if (peliculaOptional.isPresent() && cineOptional.isPresent()) {
            Pelicula pelicula = peliculaOptional.get();
            Cine cine = cineOptional.get();

            if (!pelicula.getCines().contains(cine)) {
                pelicula.getCines().add(cine);
                cine.getPeliculas().add(pelicula); // Add this line
                peliculasRepository.save(pelicula);
                cinesRepository.save(cine); // Save Cine entity as well
            } else {
                throw new RuntimeException("El cine ya está asociado con esta película");
            }
        } else {
            throw new RuntimeException("Película o Cine no encontrado");
        }
    }

    @Transactional
    public void borraCine(Long id) {
        Optional<Cine> cineOptional = cinesRepository.findById(id);
        if (cineOptional.isPresent()) {
            Cine cine = cineOptional.get();
            for (Pelicula pelicula : cine.getPeliculas()) {
                pelicula.getCines().remove(cine);
            }
            cinesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cine no encontrado con ID: " + id);
        }
    }

}
