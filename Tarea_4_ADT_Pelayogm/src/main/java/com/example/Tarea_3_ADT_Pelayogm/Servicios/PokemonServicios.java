package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonServicios {
    void insertarPokemon(Pokemon pokemon);
    List<Pokemon> obtenerTodosLosPokemon();
}
