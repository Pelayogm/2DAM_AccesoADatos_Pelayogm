package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneoRepositorio extends JpaRepository<Torneo, Long> {

    //@Query("SELECT t FROM torneo t WHERE t.idTorneo = ?1")
    //public List<Torneo> listaDeTorneos(List<Integer> listaIdTorneo);

}
