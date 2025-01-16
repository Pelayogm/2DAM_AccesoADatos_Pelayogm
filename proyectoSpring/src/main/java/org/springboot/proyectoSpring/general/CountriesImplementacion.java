package org.springboot.proyectoSpring.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountriesImplementacion implements CountriesServicio {

    @Autowired
    private final CountriesRepositorio countriesRepositorio;

    public CountriesImplementacion(CountriesRepositorio countriesRepositorio) {
        this.countriesRepositorio = countriesRepositorio;
    }

    public List<Countries> buscarTodo() {
        return countriesRepositorio.findAll();
    }

    public Optional<Countries> buscarPorId(Long id) {
        return countriesRepositorio.findById(id);
    }
}
