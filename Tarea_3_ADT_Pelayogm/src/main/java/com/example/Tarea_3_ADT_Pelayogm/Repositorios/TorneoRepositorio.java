package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorneoRepositorio extends JpaRepository<Torneo, Long> {

    @Query("SELECT t FROM Torneo t WHERE t.idAdminTorneo = ?1")
    public List<Torneo> listaDeTorneos(int idAdminTorneo);

    @Query("SELECT t FROM Torneo t WHERE t.idGanador = 0")
    public List<Torneo> listaDeTorneosSinGanador();

    @Query("SELECT t FROM Torneo t WHERE t.idGanador = -1")
    public List<Torneo> listaDeTorneosParaCombatir();

}
