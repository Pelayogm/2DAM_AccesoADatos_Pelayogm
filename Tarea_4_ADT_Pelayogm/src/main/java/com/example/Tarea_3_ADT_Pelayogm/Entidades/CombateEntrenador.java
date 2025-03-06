package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "combateEntrenador")
public class CombateEntrenador {

    @Id
    private int idCombateEntrenador;

    @OneToOne
    @JoinColumn(name = "idCombate")
    private Combate combate;

    @Column(name = "idEntrenador1")
    private int idEntrenador1;

    @Column(name = "idEntrenador2")
    private int idEntrenador2;

    @Column(name = "idGanadorCombate")
    private int idGanador;

    public CombateEntrenador(int idCombateEntrenador, Combate combate, int idEntrenador1, int idEntrenador2, int idGanador) {
        this.idCombateEntrenador = idCombateEntrenador;
        this.combate = combate;
        this.idEntrenador1 = idEntrenador1;
        this.idEntrenador2 = idEntrenador2;
        this.idGanador = idGanador;
    }

    public CombateEntrenador() {

    }

    public int getIdCombateEntrenador() {
        return idCombateEntrenador;
    }

    public Combate getCombate() {
        return combate;
    }

    public int getIdEntrenador1() {
        return idEntrenador1;
    }

    public int getIdEntrenador2() {
        return idEntrenador2;
    }

    public int getIdGanador() {
        return idGanador;
    }

    public void setIdGanador(int idGanador) {
        this.idGanador = idGanador;
    }

    public void setIdEntrenador2(int idEntrenador2) {
        this.idEntrenador2 = idEntrenador2;
    }

    public void setIdEntrenador1(int idEntrenador1) {
        this.idEntrenador1 = idEntrenador1;
    }
}
