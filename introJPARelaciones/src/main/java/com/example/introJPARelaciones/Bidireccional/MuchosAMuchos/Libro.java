package com.example.introJPARelaciones.Bidireccional.MuchosAMuchos;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "BiDireccionalLibroMuchosAMuchos")
@Table(name = "BiDireccionalLibroMuchosAMuchos")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name = "bidireccionalLibro-Autor",
            joinColumns = @JoinColumn(name = "idLibro"),
            inverseJoinColumns = @JoinColumn(name = "idAutor")
    )
    private List<Autor> listaAutores;
}
