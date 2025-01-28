package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Combate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CombateServicios {

    void insertarCombate(Combate combate);
    List<Combate> obtenerTodosLosCombatesPorIdTorneo(long idTorneo);
    Optional<Combate> obtenerCombatePorId(long idCombate);
    void actualizarCombate(Combate combate);
    void eliminarCombate(long idCombate);

}
