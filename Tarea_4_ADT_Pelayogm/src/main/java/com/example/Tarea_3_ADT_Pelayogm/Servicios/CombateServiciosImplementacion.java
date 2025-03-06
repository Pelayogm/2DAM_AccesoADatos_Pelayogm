package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Combate;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.CombateRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CombateServiciosImplementacion implements CombateServicios{

    @Autowired
    private final CombateRepositorio combateRepositorio;

    public CombateServiciosImplementacion(CombateRepositorio combateRepositorio) {
        this.combateRepositorio = combateRepositorio;
    }

    @Override
    public void insertarCombate(Combate combate) {
        combateRepositorio.saveAndFlush(combate);
    }

    @Override
    public Combate obtenerCombatePorId(long idCombate) {
        return combateRepositorio.findById(idCombate).get();
    }

    @Override
    public void actualizarCombate(Combate combate) {
        combateRepositorio.save(combate);
    }

    @Override
    public void eliminarCombate(long idCombate) {
        combateRepositorio.deleteById(idCombate);
    }

    @Override
    public List<Combate> obtenerTodosLosCombates() {
        return combateRepositorio.findAll();
    }

    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int idTorneo) {
        return combateRepositorio.obtenerTodosLosCombatesPorIdTorneo(idTorneo);
    }
}
