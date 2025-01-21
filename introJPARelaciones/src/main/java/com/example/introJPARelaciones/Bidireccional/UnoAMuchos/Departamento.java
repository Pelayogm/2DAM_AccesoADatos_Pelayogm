package com.example.introJPARelaciones.Bidireccional.UnoAMuchos;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "BiDireccionalDepartamentoUnoAMuchos")
@Table(name = "BiDireccionalDepartamentoUnoAMuchos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "departamento")
    private List<Empleado> listaDeEmpleados;

}
