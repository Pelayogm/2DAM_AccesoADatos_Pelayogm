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

    @OneToOne(mappedBy = "combate")
    private CombateEntrenador combateEntrenador;

    public Combate(int idCombate, Date fechaCombate, Torneo idTorneo, CombateEntrenador combateEntrenador) {
        this.idCombate = idCombate;
        this.fechaCombate = fechaCombate;
        this.torneo = idTorneo;
        this.combateEntrenador = combateEntrenador;
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

    public CombateEntrenador getCombateEntrenador() {
        return combateEntrenador;
    }

    public void setCombateEntrenador(CombateEntrenador combateEntrenador) {
        this.combateEntrenador = combateEntrenador;
    }
}
