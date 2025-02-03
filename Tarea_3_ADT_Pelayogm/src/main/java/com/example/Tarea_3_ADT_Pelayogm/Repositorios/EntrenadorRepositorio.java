package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Long> {
    @Query("SELECT e FROM Entrenador e WHERE e.idEntrenador NOT IN (SELECT e2.idEntrenador FROM Entrenador e2 JOIN e2.listaTorneos t WHERE t.idTorneo = ?1)")
    List<Entrenador> entrenadoresQueNoEstanEnElTorneo(int idTorneo);

}