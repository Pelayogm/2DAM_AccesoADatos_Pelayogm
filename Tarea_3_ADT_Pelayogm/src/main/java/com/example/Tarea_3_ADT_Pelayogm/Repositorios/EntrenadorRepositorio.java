package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Long> {

}
