package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Carnet;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.CarnetRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarnetServiciosImplementacion implements CarnetServicios {

    @Autowired
    private final CarnetRepositorio carnetRepositorio;

    public CarnetServiciosImplementacion(CarnetRepositorio carnetRepositorio) {
        this.carnetRepositorio = carnetRepositorio;
    }


    @Override
    public void insertarCarnet(Carnet carnet) {
        carnetRepositorio.saveAndFlush(carnet);
    }

    @Override
    public List<Carnet> obtenerTodosLosCarnets() {
        return carnetRepositorio.findAll();
    }

    @Override
    public Optional<Carnet> obtenerCarnetPorId(long id) {
        return carnetRepositorio.findById(id);
    }

    @Override
    public void actualizarCarnet(Carnet carnet) {
        carnetRepositorio.saveAndFlush(carnet);
    }

    @Override
    public void eliminarCarnet(long id) {
        carnetRepositorio.deleteById(id);
    }
}
