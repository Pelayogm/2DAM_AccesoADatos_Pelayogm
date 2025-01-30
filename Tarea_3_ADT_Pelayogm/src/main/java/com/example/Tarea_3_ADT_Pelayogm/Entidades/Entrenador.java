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
            joinColumns = @JoinColumn(name = "idTorneo"),
            inverseJoinColumns = @JoinColumn(name = "idEntrenador")
    )
    private List<Torneo> listaTorneos;

    public Entrenador(Long idEntrenador, String nombreEntrenador, String nacionalidadEntrenador, Carnet carnet) {
        this.idEntrenador = idEntrenador;
        this.nombreEntrenador = nombreEntrenador;
        this.nacionalidadEntrenador = nacionalidadEntrenador;
        this.carnetEntrenador = carnet;
    }

    public Entrenador() {

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
