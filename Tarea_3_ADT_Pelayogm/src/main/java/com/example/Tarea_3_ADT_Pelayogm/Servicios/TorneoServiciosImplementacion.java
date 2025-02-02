package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.TorneoAdmin;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.TorneoAdminRepositorio;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.TorneoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TorneoServiciosImplementacion implements TorneoServicios {

    @Autowired
    private TorneoRepositorio torneoRepositorio;
    @Autowired
    TorneoAdminRepositorio torneoAdminRepositorio;

    public TorneoServiciosImplementacion() {
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
    public Torneo obtenerTorneoPorId(long idTorneo) {
        return torneoRepositorio.findById(idTorneo).get();
    }

    @Override
    public void actualizarTorneo(Torneo torneo) {
        torneoRepositorio.save(torneo);
    }

    @Override
    public void eliminarTorneo(long idTorneo) {
        torneoRepositorio.deleteById(idTorneo);
    }

    public List<Torneo> torneosDelAdministrador(int idAdminTorneos) {
        return torneoRepositorio.listaDeTorneos(idAdminTorneos);
    }

    public List<Torneo> listaDeTorneosSinGanador() {
        return torneoRepositorio.listaDeTorneosSinGanador();
    }

    public List<Torneo> listaDeTorneosParaCombatir() {
        return torneoRepositorio.listaDeTorneosParaCombatir();
    }

}
