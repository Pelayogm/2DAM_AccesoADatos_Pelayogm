package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.CombateEntrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombateEntrenadorRepositorio extends JpaRepository<CombateEntrenador, Long> {
    /*
    @Query("SELECT t FROM Entrenador t WHERE t.id NOT IN (SELECT c.idEntrenador1 FROM CombateEntrenador c) AND t.id NOT IN (SELECT c.idEntrenador2 FROM CombateEntrenador c)")
    List<Entrenador> listaDeEntrenadoresQueNoEstan(int idEntrenador);
    */

}
