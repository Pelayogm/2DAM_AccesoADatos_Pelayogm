package com.example.Tarea_3_ADT_Pelayogm;

import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tarea3AdtPelayogmApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tarea3AdtPelayogmApplication.class, args);
		Menus.menuInicial();
	}

}

//NOTAS.

//Clase Combate
// - Añadir idEntrenador1 e idEntrenador2

//Clase Sesion
// - Hacer el metodo IniciarSesion() y si no hay cuenta notificar (COMPLETADO)

//Clase Menus
// - Acabar el metodo crearEntrenador()
// - Hacer el metodo menuAdminTorneos()
// - Hacer el metodo menuAdministradorGeneral()

//Clase Funciones
// - Hacer el exportarCarnet()
// - Hacer el exportarTorneo()

//Repositorios
// - Hacer el metodo buscarCombatePorIdTorneo()

//Entidades
//Hacer las relaciones (ManyToMany, ManyToOne...)
// Da error el mapeo de EntrenadorYCarnet (CORREGIDO)
