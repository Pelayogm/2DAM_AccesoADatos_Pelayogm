package com.example.Tarea_3_ADT_Pelayogm.Entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "carnet")
public class Carnet {

    @Id
    private Long idCarnet;

    @OneToOne(mappedBy = "carnetEntrenador")
    private Entrenador entrenador;

    @Column(name = "fechaExpedicion", nullable = false)
    private LocalDate fechaExpedicion;

    @Column(name = "puntosCarnet", nullable = false)
    private Float puntosCarnet;

    @Column(name = "numeroVictorias", nullable = false)
    private Integer numeroVictorias;


    public Carnet(Long idCarnet, LocalDate fechaExpedicion, Float puntosCarnet, Integer numeroVictorias) {
        this.idCarnet = idCarnet;
        this.fechaExpedicion = fechaExpedicion;
        this.puntosCarnet = puntosCarnet;
        this.numeroVictorias = numeroVictorias;
    }

    public Carnet() {

    }

    public Long getIdCarnet() {
        return idCarnet;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public Float getPuntosCarnet() {
        return puntosCarnet;
    }

    public void setPuntosCarnet(Float puntosCarnet) {
        this.puntosCarnet = puntosCarnet;
    }

    public Integer getNumeroVictorias() {
        return numeroVictorias;
    }

    public void setNumeroVictorias(Integer numeroVictorias) {
        this.numeroVictorias = numeroVictorias;
    }
}
