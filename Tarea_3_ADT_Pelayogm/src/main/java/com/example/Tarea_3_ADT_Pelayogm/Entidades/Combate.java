package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "combate")
public class Combate {

    @Id
    private int idCombate;

    @Column(name = "fechaCombate")
    private Date fechaCombate;

    @ManyToOne
    @JoinColumn(name = "idTorneo", nullable = false)
    private Torneo torneo;

    public Combate(int idCombate, Date fechaCombate, Torneo idTorneo) {
        this.idCombate = idCombate;
        this.fechaCombate = fechaCombate;
        this.torneo = idTorneo;
    }

    public Combate() {

    }

    public int getIdCombate() {
        return idCombate;
    }

    public Date getFechaCombate() {
        return fechaCombate;
    }

    public Torneo getIdTorneo() {
        return torneo;
    }

    public Torneo getTorneo() {
        return torneo;
    }
}
