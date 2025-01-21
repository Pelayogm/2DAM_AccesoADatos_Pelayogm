package com.example.introJPARelaciones.Unidireccional.MuchosAUno;

import jakarta.persistence.*;

@Entity(name = "EscuelaMuchosAUno")
@Table(name = "EscuelaMuchosAUno")
public class Escuela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
