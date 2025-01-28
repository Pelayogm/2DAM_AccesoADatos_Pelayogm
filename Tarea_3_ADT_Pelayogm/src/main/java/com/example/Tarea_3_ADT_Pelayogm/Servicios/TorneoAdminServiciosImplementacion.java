package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.TorneoAdmin;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.TorneoAdminRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneoAdminServiciosImplementacion implements TorneoAdminServicios {

    @Autowired
    private final TorneoAdminRepositorio torneoAdminRepositorio;

    public TorneoAdminServiciosImplementacion(TorneoAdminRepositorio torneoAdminRepositorio) {
        this.torneoAdminRepositorio = torneoAdminRepositorio;
    }

    @Override
    public void insertarTorneoAdmin(TorneoAdmin torneoAdmin) {
        torneoAdminRepositorio.save(torneoAdmin);
    }

    @Override
    public List<TorneoAdmin> obtenerTodosLosAdminTorneo() {
        return torneoAdminRepositorio.findAll();
    }

    @Override
    public int obtenerIdAdminTorneo(long idTorneo) {
        TorneoAdmin torneoAdmin = torneoAdminRepositorio.getById(idTorneo);
        return torneoAdmin.getIdAdminTorneos();
    }

    @Override
    public List<TorneoAdmin> obtenerTorneosPorIdAdmin(long idAdmin) {
        return List.of();
    }

    @Override
    public void eliminarTorneoAdmin(long idTorneo) {
        torneoAdminRepositorio.deleteById(idTorneo);
    }
}
