package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "combate")
public class Combate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCombate;

    @Column(name = "fechaCombate")
    private Date fechaCombate;

    @Column(name = "idTorneo")
    private int idTorneo;

    @ManyToOne
    private Torneo torneo;

    public Combate(int idCombate, Date fechaCombate, int idTorneo) {
        this.idCombate = idCombate;
        this.fechaCombate = fechaCombate;
        this.idTorneo = idTorneo;
    }

    public Combate() {

    }

    public int getIdCombate() {
        return idCombate;
    }

    public Date getFechaCombate() {
        return fechaCombate;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public Torneo getTorneo() {
        return torneo;
    }
}
