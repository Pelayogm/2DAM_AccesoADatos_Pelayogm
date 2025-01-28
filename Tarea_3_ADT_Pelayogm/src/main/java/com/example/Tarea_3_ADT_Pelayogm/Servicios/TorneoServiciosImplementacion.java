package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.TorneoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoServiciosImplementacion implements TorneoServicios {

    @Autowired
    private final TorneoRepositorio torneoRepositorio;

    public TorneoServiciosImplementacion(TorneoRepositorio torneoRepositorio) {
        this.torneoRepositorio = torneoRepositorio;
    }

    @Override
    public void insertarTorneo(Torneo torneo) {
        torneoRepositorio.save(torneo);
    }

    @Override
    public List<Torneo> obtenerTodosLosTorneos() {
        return torneoRepositorio.findAll();
    }

    @Override
    public Optional<Torneo> obtenerTorneoPorId(long idTorneo) {
        return torneoRepositorio.findById(idTorneo);
    }

    @Override
    public void actualizarTorneo(Torneo torneo) {
        torneoRepositorio.save(torneo);
    }

    @Override
    public void eliminarTorneo(long idTorneo) {
        torneoRepositorio.deleteById(idTorneo);
    }
}
