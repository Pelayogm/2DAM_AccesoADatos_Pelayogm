package com.example.introJPARelaciones.Bidireccional.MuchosAUno;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "BiDireccionalEscuelaMuchosAUno")
@Table(name = "BiDireccionalEscuelaMuchosAUno")
public class Escuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "escuela")
    private List<Estudiante> listaDeEstudiantes;

}
