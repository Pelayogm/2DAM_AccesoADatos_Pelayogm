package com.example.introJPARelaciones.Unidireccional.MuchosAUno;

import jakarta.persistence.*;

@Entity(name = "EstudianteMuchosAUno")
@Table(name = "EstudianteMuchosAUno")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "idEscuela")
    private Escuela escuela;
}
