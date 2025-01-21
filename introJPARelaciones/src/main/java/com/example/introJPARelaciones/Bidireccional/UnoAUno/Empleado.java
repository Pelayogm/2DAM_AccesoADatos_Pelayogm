package com.example.introJPARelaciones.Bidireccional.UnoAUno;

import jakarta.persistence.*;

@Entity(name = "BiDireccionalEmpleadoUnoAUno")
@Table(name = "BiDireccionalEmpleadoUnoAUno")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "parkingID")
    private Parking plaza;
}
