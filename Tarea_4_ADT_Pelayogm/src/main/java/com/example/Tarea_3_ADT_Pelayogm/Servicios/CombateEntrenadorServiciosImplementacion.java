package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.CombateEntrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.CombateEntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CombateEntrenadorServiciosImplementacion implements CombateEntrenadorServicios {

    @Autowired
    private final CombateEntrenadorRepositorio combateEntrenadorRepositorio;

    public CombateEntrenadorServiciosImplementacion(CombateEntrenadorRepositorio combateEntrenadorRepositorio) {
        this.combateEntrenadorRepositorio = combateEntrenadorRepositorio;
    }

    @Override
    public void insertarCombateEntrenador(CombateEntrenador combateEntrenador) {
        combateEntrenadorRepositorio.saveAndFlush(combateEntrenador);
    }

    @Override
    public List<CombateEntrenador> obtenerTodosLosCombateEntrenador() {
        return combateEntrenadorRepositorio.findAll();
    }

    @Override
    public CombateEntrenador obtenerCombateEntrenadorPorId(int id) {
        return combateEntrenadorRepositorio.findById((long) id).get();
    }

    @Override
    public void actualizarCombateEntrenador(CombateEntrenador combateEntrenador) {
        combateEntrenadorRepositorio.saveAndFlush(combateEntrenador);
    }

    @Override
    public void eliminarCombateEntrenador(int id) {
        combateEntrenadorRepositorio.deleteById((long) id);
    }
}
