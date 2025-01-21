package com.example.introJPARelaciones.Unidireccional.MuchosAMuchos;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "LibroMuchosAMuchos")
@Table(name = "LibroMuchosAMuchos")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
