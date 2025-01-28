package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorneoRepositorio extends JpaRepository<Torneo, Long> {

}
