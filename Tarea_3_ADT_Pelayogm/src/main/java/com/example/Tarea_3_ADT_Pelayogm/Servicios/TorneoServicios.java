package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TorneoServicios {

    void insertarTorneo(Torneo torneo);
    List<Torneo> obtenerTodosLosTorneos();
    Torneo obtenerTorneoPorId(long idTorneo);
    void actualizarTorneo(Torneo torneo);
    void eliminarTorneo(long idTorneo);

}
