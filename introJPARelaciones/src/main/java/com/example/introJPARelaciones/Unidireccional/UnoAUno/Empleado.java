package com.example.introJPARelaciones.Unidireccional.UnoAUno;

import jakarta.persistence.*;

@Entity(name = "EmpleadoUnoAUno")
@Table(name = "EmpleadoUnoAUno")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "parkingID")
    private Parking plaza;
}
