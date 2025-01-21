package com.example.introJPARelaciones.Unidireccional.MuchosAMuchos;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "AutorMuchosAMuchos")
@Table(name = "AutorMuchosAMuchos")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "libro-autor",
            joinColumns = @JoinColumn(name = "idLibro"),
            inverseJoinColumns = @JoinColumn(name = "idAutor")
    )
    private List<Libro> libros;
}
