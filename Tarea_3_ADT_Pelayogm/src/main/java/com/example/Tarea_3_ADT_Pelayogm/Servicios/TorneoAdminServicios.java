package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.TorneoAdmin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TorneoAdminServicios {

    void insertarTorneoAdmin(TorneoAdmin torneoAdmin);
    List<TorneoAdmin> obtenerTodosLosAdminTorneo();
    int obtenerIdAdminTorneo(long idTorneo);
    List<TorneoAdmin> obtenerTorneosPorIdAdmin(long idAdmin);
    void eliminarTorneoAdmin(long idTorneo);
}
