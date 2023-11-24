package com.cev.prueba.prueba.web;

import com.cev.prueba.prueba.domain.Cine;
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
public class CinesController {

    CinesPersistService cinesPersistService;
    PeliculasPersistService peliculasPersistService;

    @Autowired
    public CinesController(PeliculasPersistService peliculasPersistService, CinesPersistService cinesPersistService) {
        this.peliculasPersistService = peliculasPersistService;
        this.cinesPersistService = cinesPersistService;
    }

    @GetMapping(path = "/api/cines")
    List<Cine> getCines(@RequestParam(required = false) String nombre) {
        return cinesPersistService.getCines();
    }

    @GetMapping(path = "/api/cines/{id}")
    Cine getCine(@PathVariable Long id)
    {
        return cinesPersistService.getCine(id);
    }

    @PostMapping(path = "/api/cines")
    Long altaCine(@RequestBody Cine cine)
    {
        return cinesPersistService.addCine(cine);
    }

    @PutMapping(path = "/api/cines/{id}")
    Cine modificaCine(@RequestBody Cine cine, @PathVariable Long id)
    {
        cinesPersistService.guardaCine(id, cine);
        return cine;
    }

    @DeleteMapping(path = "/api/cines/{id}")
    public ResponseEntity<String> borraCine(@PathVariable Long id) {
        try {
            cinesPersistService.borraCine(id);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(path = "cineHeader")
    ResponseEntity<List<Cine>> getCinesHeader()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("MiHeaderRespuesta", "HeaderRespuesta");

        return ResponseEntity.ok().headers(headers).body(cinesPersistService.getCines());
    }

    @GetMapping("/api/cines/porcodigopostal/{codigoPostal}")
    public List<Cine> getCinesPorCodigoPostal(@PathVariable int codigoPostal) {
        return cinesPersistService.getCinesPorCodigoPostal(codigoPostal);
    }

    @GetMapping("api/cines/porprecioasc")
    public List<Cine> getCinesPorPrecioAsc() {
        return cinesPersistService.getCinesPorPrecioAsc();
    }
}
