package com.example.introJPARelaciones.Unidireccional.UnoAUno;

import jakarta.persistence.*;

@Entity(name = "ParkingUnoAUno")
@Table(name = "ParkingUnoAUno")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
