package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "torneoAdmin")
public class TorneoAdmin {

    @Id
    private final int idTorneo;

    @Column(name = "idAdminTorneos")
    private final int idAdminTorneos;

    public TorneoAdmin(int idTorneo, int idAdminTorneos) {
        this.idTorneo = idTorneo;
        this.idAdminTorneos = idAdminTorneos;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public int getIdAdminTorneos() {
        return idAdminTorneos;
    }
}
