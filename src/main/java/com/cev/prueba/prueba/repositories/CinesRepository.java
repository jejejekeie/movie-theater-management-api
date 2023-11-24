package com.cev.prueba.prueba.repositories;

import com.cev.prueba.prueba.domain.Cine;
import com.cev.prueba.prueba.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinesRepository extends JpaRepository<Cine, Long> {
    List<Cine> findByOrderByPrecioAsc();
    List<Cine> findByCodigoPostal(int codigoPostal);
    Cine getReferenceById(Long id);
}
