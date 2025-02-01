package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "entrenador")
public class Entrenador extends Usuario {
    @Id
    private long idEntrenador;

    @Column(name = "nombreEntrenador", nullable = false)
    private String nombreEntrenador;

    @Column(name = "nacionalidadEntrenador", nullable = false)
    private String nacionalidadEntrenador;

    @OneToOne
    private Carnet carnetEntrenador;

    @ManyToMany
    @JoinTable(
            name = "entrenador_torneo",
            inverseJoinColumns = @JoinColumn(name = "idTorneo"),
            joinColumns = @JoinColumn(name = "idEntrenador")
    )
    private List<Torneo> listaTorneos;

    public Entrenador(Long idEntrenador, String nombreEntrenador, String nacionalidadEntrenador, Carnet carnet) {
        super.setEstadoSesion(true);
        this.idEntrenador = idEntrenador;
        this.nombreEntrenador = nombreEntrenador;
        this.nacionalidadEntrenador = nacionalidadEntrenador;
        this.carnetEntrenador = carnet;
    }

    public Entrenador() {
        setEstadoSesion(true);
    }

    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public String getNacionalidadEntrenador() {
        return nacionalidadEntrenador;
    }

    public Carnet getCarnetEntrenador() {
        return carnetEntrenador;
    }
}
