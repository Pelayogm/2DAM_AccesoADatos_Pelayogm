package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.TorneoAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneoAdminRepositorio extends JpaRepository<TorneoAdmin, Long> {

    //@Query("SELECT t FROM torneoAdmin t where t.idAdminTorneos = ?1")
    //List<TorneoAdmin> listaTorneosAdmin(int idAdminTorneo);
}
