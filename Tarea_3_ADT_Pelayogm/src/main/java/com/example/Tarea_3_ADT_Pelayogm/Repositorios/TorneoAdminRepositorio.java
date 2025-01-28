package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.TorneoAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorneoAdminRepositorio extends JpaRepository<TorneoAdmin, Long> {

}
