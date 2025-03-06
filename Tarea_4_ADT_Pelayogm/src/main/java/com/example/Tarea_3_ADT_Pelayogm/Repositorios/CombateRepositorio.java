package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Combate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombateRepositorio extends JpaRepository<Combate, Long> {

    @Query("SELECT t FROM Combate t WHERE t.torneo = ?1")
    public List<Combate> obtenerTodosLosCombatesPorIdTorneo(int idTorneo);
}
