package com.example.Tarea_3_ADT_Pelayogm;

import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tarea3AdtPelayogmApplication implements CommandLineRunner {
	@Autowired
	Menus menus;

	public static void main(String[] args) {
		SpringApplication.run(Tarea3AdtPelayogmApplication.class, args);
	}

	@Override
	public void run (String... arg0) {
		menus.menuInicial();
	}

}

//En clase root- y en casa root-root
//Acabar relaciones BBDD
//EXCEPCION POR LAS QUERY PARA SACAR LOS TORNEOS ADMIN y por las query
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
// - Hacer el exportarCarnet() (COMPLETADO)
// - Hacer el exportarTorneo()

//Repositorios
// - Hacer el metodo buscarCombatePorIdTorneo()

//Entidades
// - Hacer las relaciones (ManyToMany, ManyToOne...)
// - Da error el mapeo de EntrenadorYCarnet (CORREGIDO)
// - Poner ganador del combate (idEntrenador)
// - Poner ganador del torneo
// - Tabla Torneo-Combate / Torneo-Entrenador

//DUDAS
// HACER SINGLETON PARA LA CONEXION E INTERCAMBIO CON LA BASE DE DATOS (SOLUCIONADO)
