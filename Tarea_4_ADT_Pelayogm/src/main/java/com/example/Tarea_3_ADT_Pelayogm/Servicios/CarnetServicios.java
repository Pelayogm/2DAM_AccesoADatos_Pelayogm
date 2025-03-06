package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Carnet;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarnetServicios {

    void insertarCarnet(Carnet carnet);
    public List<Carnet> obtenerTodosLosCarnets();
    Carnet obtenerCarnetPorId(long id);
    void actualizarCarnet(Carnet carnet);
    void eliminarCarnet(long id);

}
