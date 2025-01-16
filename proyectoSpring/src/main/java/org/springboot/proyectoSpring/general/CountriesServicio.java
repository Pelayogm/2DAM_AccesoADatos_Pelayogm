package org.springboot.proyectoSpring.general;

import java.util.List;
import java.util.Optional;

public interface CountriesServicio {

    public List<Countries> buscarTodo();

    public Optional<Countries> buscarPorId(Long id);
}
