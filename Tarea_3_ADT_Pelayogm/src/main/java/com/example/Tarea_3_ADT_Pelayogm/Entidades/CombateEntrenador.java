package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "combateEntrenador")
public class CombateEntrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int idCombateEntrenador;

    @Column(name = "idCombate")
    private final int idCombate;

    @Column(name = "idEntrenador1")
    private final int idEntrenador1;

    @Column(name = "idEntrenador2")
    private final int idEntrenador2;

    public CombateEntrenador(int idCombateEntrenador, int idCombate, int idEntrenador1, int idEntrenador2) {
        this.idCombateEntrenador = idCombateEntrenador;
        this.idCombate = idCombate;
        this.idEntrenador1 = idEntrenador1;
        this.idEntrenador2 = idEntrenador2;
    }

    public int getIdCombateEntrenador() {
        return idCombateEntrenador;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public int getIdEntrenador1() {
        return idEntrenador1;
    }

    public int getIdEntrenador2() {
        return idEntrenador2;
    }
}
