package com.example.introJPARelaciones.Bidireccional.UnoAUno;

import jakarta.persistence.*;

@Entity(name = "BiDireccionalParkingUnoAUno")
@Table(name = "BiDireccionalParkingUnoAUno")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(mappedBy = "plaza")
    private Empleado empleado;
}
