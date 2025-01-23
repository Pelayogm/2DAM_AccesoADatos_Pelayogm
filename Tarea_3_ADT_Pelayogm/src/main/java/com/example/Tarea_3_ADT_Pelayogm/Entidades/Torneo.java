package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "torneo")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer idTorneo;

    @Column(name = "nombreTorneo")
    private final String nombreTorneo;

    @Column(name = "codigoTorneo")
    private final String codigoTorneo;

    @Column(name = "puntosTorneo")
    private final double puntosVictoriaTorneo;

    public Torneo(Integer idTorneo, String nombreTorneo, String codigoTorneo, double puntosVictoriaTorneo) {
        this.idTorneo = idTorneo;
        this.nombreTorneo = nombreTorneo;
        this.codigoTorneo = codigoTorneo;
        this.puntosVictoriaTorneo = puntosVictoriaTorneo;
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
