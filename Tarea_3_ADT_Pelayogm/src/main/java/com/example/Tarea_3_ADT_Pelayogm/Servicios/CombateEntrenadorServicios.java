package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.CombateEntrenador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CombateEntrenadorServicios {
    void insertarCombateEntrenador(CombateEntrenador combateEntrenador);
    public List<CombateEntrenador> obtenerTodosLosCombateEntrenador();
    CombateEntrenador obtenerCombateEntrenadorPorId(int id);
    void actualizarCombateEntrenador(CombateEntrenador combateEntrenador);
    void eliminarCombateEntrenador(int id);
}
