package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.CombateEntrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombateEntrenadorRepositorio extends JpaRepository<CombateEntrenador, Long> {

}
