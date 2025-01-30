package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorServiciosImplementacion implements EntrenadorServicios {

    @Autowired
    private final EntrenadorRepositorio entrenadorRepositorio;

    public EntrenadorServiciosImplementacion(EntrenadorRepositorio entrenadorRepositorio) {
        this.entrenadorRepositorio = entrenadorRepositorio;
    }

    @Override
    public void insertarEntrenador(Entrenador entrenador) {
        entrenadorRepositorio.saveAndFlush(entrenador);
    }

    @Override
    public List<Entrenador> obtenerTodosLosEntrenadores() {
        return entrenadorRepositorio.findAll();
    }

    @Override
    public Entrenador obtenerEntrenadorPorId(long idEntrenador) {
        return entrenadorRepositorio.findById(idEntrenador).get();
    }

    @Override
    public void actualizarEntrenador(Entrenador entrenador) {
        entrenadorRepositorio.save(entrenador);
    }

    @Override
    public void eliminarEntrenador(long idEntrenador) {
        entrenadorRepositorio.deleteById(idEntrenador);
    }
}
