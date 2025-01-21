package com.example.introJPARelaciones.Unidireccional.UnoAMuchos;

import jakarta.persistence.*;

@Entity(name = "EmpleadoUnoAMuchos")
@Table(name = "EmpleadoUnoAMuchos")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
