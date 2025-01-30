package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntrenadorServicios {

    void insertarEntrenador(Entrenador entrenador);
    List<Entrenador> obtenerTodosLosEntrenadores();
    Entrenador obtenerEntrenadorPorId(long idEntrenador);
    void actualizarEntrenador(Entrenador entrenador);
    void eliminarEntrenador(long idEntrenador);
}
