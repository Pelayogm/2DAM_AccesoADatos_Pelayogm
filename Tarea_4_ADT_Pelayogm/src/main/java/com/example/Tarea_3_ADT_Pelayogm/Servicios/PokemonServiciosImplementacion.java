package com.example.Tarea_3_ADT_Pelayogm.Servicios;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Pokemon;
import com.example.Tarea_3_ADT_Pelayogm.Repositorios.PokemonRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiciosImplementacion implements PokemonServicios {

    @Autowired
    private final PokemonRepositorio pokemonRepositorio;

    public PokemonServiciosImplementacion(PokemonRepositorio pokemonRepositorio) {
        this.pokemonRepositorio = pokemonRepositorio;
    }

    @Override
    public void insertarPokemon(Pokemon pokemon) {
        pokemonRepositorio.saveAndFlush(pokemon);
    }

    @Override
    public List<Pokemon> obtenerTodosLosPokemon() {
        return pokemonRepositorio.findAll();
    }
}
