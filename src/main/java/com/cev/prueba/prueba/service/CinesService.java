package com.cev.prueba.prueba.service;

import com.cev.prueba.prueba.domain.Cine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinesService {

    final List<Cine> cines = new ArrayList<>();

    public CinesService() {
        Cine cine = new Cine();
        cine.setNombre("Ideal");
        cine.setProvincia("Madrid");
        cine.setPoblacion("Madrid");
        cine.setCodigoPostal(39012);
        cine.setPrecio(7.90);
        cines.add(cine);
    }

    public Cine getCine(int id) {
        return cines.get(id-1);
    }

    public int addCine(Cine cine) {
        cines.add(cine);
        return cines.size();
    }

    public List<Cine> getCines() {
        return cines;
    }

    public void guardaCine(int id, Cine pelicula ) {
        cines.set(id-1, pelicula);
    }

    public void borraCine(int id) {
        cines.remove(id-1);
    }

    public  List<Cine> buscaPorNombre(String nombre) {

        List<Cine> cinesResultado = new ArrayList<>();
        for (Cine cine : cines) {
            if (cine.getNombre().contains(nombre)) {
                cinesResultado.add(cine);
            }
        }
        return cinesResultado;
    }

}
