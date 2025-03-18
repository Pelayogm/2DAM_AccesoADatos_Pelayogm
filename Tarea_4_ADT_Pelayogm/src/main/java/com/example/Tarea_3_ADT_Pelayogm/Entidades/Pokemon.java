package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idEntrenador", nullable = false)
    private Entrenador entrenador;

    @Column(name = "nombrePokemon")
    private String nombrePokemon;

    @Column(name = "tipoPokemon")
    private String tipo;

    @Column(name = "nivelPokemon")
    private int nivel;

    public Pokemon(Integer id, Entrenador entrenador, String nombrePokemon, String tipo, int nivel) {
        this.id = id;
        this.entrenador = entrenador;
        this.nombrePokemon = nombrePokemon;
        this.tipo = tipo;
        this.nivel = nivel;
    }

    public Pokemon() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public String getNombrePokemon() {
        return nombrePokemon;
    }

    public void setNombrePokemon(String nombrePokemon) {
        this.nombrePokemon = nombrePokemon;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
