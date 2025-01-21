package com.example.introJPARelaciones.Bidireccional.MuchosAMuchos;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "BiDireccionalAutorMuchosAMuchos")
@Table(name = "BiDireccionalAutorMuchosAMuchos")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "listaAutores")
    private List<Libro> libros;

    // @ManyToMany
    //    @JoinTable(
    //            name = "Bilibro-autor",
    //            joinColumns = @JoinColumn(name = "idLibro"),
    //            inverseJoinColumns = @JoinColumn(name = "idAutor")
    //    )
    //    private List<Libro> libros;
}
