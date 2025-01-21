package com.example.introJPARelaciones.Bidireccional.UnoAMuchos;

import jakarta.persistence.*;

@Entity(name = "BiDireccionalEmpleadoUnoAMuchos")
@Table(name = "BiDireccionalEmpleadoUnoAMuchos")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento departamento;
}
