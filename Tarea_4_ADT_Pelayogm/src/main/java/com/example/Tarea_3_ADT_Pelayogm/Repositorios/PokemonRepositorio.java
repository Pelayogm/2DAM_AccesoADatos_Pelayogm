package com.example.Tarea_3_ADT_Pelayogm.Repositorios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepositorio extends JpaRepository<Pokemon, Long> {
}
