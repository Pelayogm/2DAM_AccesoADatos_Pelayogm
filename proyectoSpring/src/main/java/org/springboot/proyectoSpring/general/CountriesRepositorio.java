package org.springboot.proyectoSpring.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepositorio extends JpaRepository<Countries, Long> {

}
