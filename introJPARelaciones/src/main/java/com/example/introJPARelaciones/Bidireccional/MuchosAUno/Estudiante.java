package com.example.introJPARelaciones.Bidireccional.MuchosAUno;

import jakarta.persistence.*;

@Entity(name = "BiDireccionalEstudianteMuchosAUno")
@Table(name = "BiDireccionalEstudianteMuchosAUno")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idEscuela")
    private Escuela escuela;
}
