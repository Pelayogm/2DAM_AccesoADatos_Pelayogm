package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "torneo")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTorneo;

    @Column(name = "nombreTorneo")
    private String nombreTorneo;

    @Column(name = "codigoTorneo")
    private String codigoTorneo;

    @Column(name = "puntosTorneo")
    private double puntosVictoriaTorneo;

    @OneToMany(mappedBy = "torneo")
    private ArrayList<Combate> combates;

    @ManyToMany(mappedBy = "listaTorneos")
    private List<Entrenador> listaEntrenadores;

    public Torneo(Integer idTorneo, String nombreTorneo, String codigoTorneo, double puntosVictoriaTorneo) {
        this.idTorneo = idTorneo;
        this.nombreTorneo = nombreTorneo;
        this.codigoTorneo = codigoTorneo;
        this.puntosVictoriaTorneo = puntosVictoriaTorneo;
    }

    public Torneo() {
    }

    public Integer getIdTorneo() {
        return idTorneo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public String getCodigoTorneo() {
        return codigoTorneo;
    }

    public double getPuntosVictoriaTorneo() {
        return puntosVictoriaTorneo;
    }
}
