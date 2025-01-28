package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "entrenador")
public class Entrenador {
    @Id
    private final long idEntrenador;

    @Column(name = "nombreEntrenador", nullable = false)
    private final String nombreEntrenador;

    @Column(name = "nacionalidadEntrenador", nullable = false)
    private final String nacionalidadEntrenador;

    //@OneToOne(mappedBy = "entrenador")
    //private Carnet carnetEntrenador;

    public Entrenador(Long idEntrenador, String nombreEntrenador, String nacionalidadEntrenador) {
        this.idEntrenador = idEntrenador;
        this.nombreEntrenador = nombreEntrenador;
        this.nacionalidadEntrenador = nacionalidadEntrenador;
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
}
