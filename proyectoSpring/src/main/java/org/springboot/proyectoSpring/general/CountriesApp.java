package org.springboot.proyectoSpring.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountriesApp implements CommandLineRunner {

    @Autowired
    CountriesImplementacion countriesImplementacion;

    public static void main (String [] args) {
        SpringApplication.run(CountriesApp.class, args);
    }

    @Override
    public void run (String... arg0) {
        countriesImplementacion.buscarTodo().forEach(countries -> System.out.println(countries.getName()));
    }
}
