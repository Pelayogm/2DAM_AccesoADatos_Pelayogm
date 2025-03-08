package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "entrenador_torneo",
            inverseJoinColumns = @JoinColumn(name = "idTorneo"),
            joinColumns = @JoinColumn(name = "idEntrenador")
    )
    private List<Torneo> listaTorneos;

    public Entrenador(long idEntrenador, String nombreEntrenador, String nacionalidadEntrenador, Carnet carnet) {
        super.setEstadoSesion(true);
        this.idEntrenador = idEntrenador;
        this.nombreEntrenador = nombreEntrenador;
        this.nacionalidadEntrenador = nacionalidadEntrenador;
        this.carnetEntrenador = carnet;
        setIdUsuarioInterfaz((int) idEntrenador);
        listaTorneos = new ArrayList<>();
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

    public List<Torneo> getListaTorneos() {
        return listaTorneos;
    }

    public void setListaTorneos(List<Torneo> listaTorneos) {
        this.listaTorneos = listaTorneos;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nacionalidadEntrenador='" + nacionalidadEntrenador + '\'' +
                ", idEntrenador=" + idEntrenador +
                ", nombreEntrenador='" + nombreEntrenador + '\'' +
                '}';
    }
}
