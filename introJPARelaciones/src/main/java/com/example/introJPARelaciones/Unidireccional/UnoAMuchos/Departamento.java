package com.example.introJPARelaciones.Unidireccional.UnoAMuchos;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "DepartamentoUnoAMuchos")
@Table(name = "DepartamentoUnoAMuchos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    @JoinColumn(name = "idDepartamento")
    private List<Empleado> listaDeEmpleados;

}
