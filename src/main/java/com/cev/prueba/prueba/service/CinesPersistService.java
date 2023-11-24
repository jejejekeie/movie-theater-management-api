package com.cev.prueba.prueba.service;

import com.cev.prueba.prueba.domain.Cine;
import com.cev.prueba.prueba.domain.Pelicula;
import com.cev.prueba.prueba.repositories.CinesRepository;
import com.cev.prueba.prueba.repositories.PeliculasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CinesPersistService {

    final
    CinesRepository cinesRepository;
    final
    PeliculasRepository peliculasRepository;

    @Autowired
    public CinesPersistService(PeliculasRepository peliculasRepository, CinesRepository cinesRepository) {
        this.cinesRepository = cinesRepository;
        this.peliculasRepository = peliculasRepository;
        System.out.println("CinesRepository injected: " + (this.cinesRepository != null));
    }

    @Transactional
    public Cine getCine(Long id) {
        Cine cine = cinesRepository.findById(id).orElseThrow(() -> new RuntimeException("Cine not found"));
        cine.getPeliculas().size();
        return cine;
    }

    public Long addCine(Cine cine) {
        Cine cineGuardado = cinesRepository.save(cine);
        return cineGuardado.getId();
    }

    public List<Cine> getCines() {
        return cinesRepository.findAll();
    }

    public void guardaCine(Long id, Cine pelicula) {
        Cine cineGuardado = cinesRepository.getReferenceById(id);
        cineGuardado.setNombre(pelicula.getNombre());
        cineGuardado.setProvincia(pelicula.getProvincia());
        cineGuardado.setPoblacion(pelicula.getPoblacion());
        cineGuardado.setCodigoPostal(pelicula.getCodigoPostal());
        cineGuardado.setPrecio(pelicula.getPrecio());
        cinesRepository.save(cineGuardado);
    }

    public void borraCine(Long id) {
        Optional<Cine> cineOptional = cinesRepository.findById(id);
        if (cineOptional.isPresent()) {
            Cine cine = cineOptional.get();
            for (Pelicula pelicula : cine.getPeliculas()) {
                pelicula.getCines().remove(cine);
                peliculasRepository.save(pelicula);
            }
            cinesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cine no encontrado con ID: " + id);
        }
    }

    public List<Cine> getCinesPorPrecioAsc() {
        return cinesRepository.findByOrderByPrecioAsc();
    }

    public List<Cine> getCinesPorCodigoPostal(int codigoPostal) {
        return cinesRepository.findByCodigoPostal(codigoPostal);
    }
}
