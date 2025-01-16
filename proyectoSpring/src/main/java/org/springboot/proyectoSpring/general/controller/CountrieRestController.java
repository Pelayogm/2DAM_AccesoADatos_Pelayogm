package org.springboot.proyectoSpring.general.controller;

import org.springboot.proyectoSpring.general.Countries;
import org.springboot.proyectoSpring.general.CountriesServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/countries")
public class CountrieRestController {
        private final CountriesServicio countriesServicio;

    public CountrieRestController(CountriesServicio countriesServicio) {
        this.countriesServicio = countriesServicio;
    }

    @GetMapping
    @ResponseBody
    public List<Countries> obtenerTodos() {
        return countriesServicio.buscarTodo();
    }
}
