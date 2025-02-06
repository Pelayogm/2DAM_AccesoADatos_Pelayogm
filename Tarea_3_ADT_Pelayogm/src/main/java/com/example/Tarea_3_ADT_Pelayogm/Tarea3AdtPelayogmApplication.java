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
//Mostrar puntos del carnet y preguntar que puntos del carnet
//Borrar torneo-admin
//Se inserta 2 veces un entrenador en la tabla entrenador_torneo (SOLUCIONADO)
//Corregir el metodo combatir, el ultimo combate es el que importa y se deberia usar un contador de victorias o hacerlo aleatorio
//En un torneo 3 entreneadores y 2 combates por entrenador (SOLUCIONADO)
//La lista de torneos del entrenador es null y da no deja guardar torneo pero lo inscribe igual en inscribir() (CORREGIDO)
//Inscribir entrenador (sale un torneo lleno y no deberia no pone el id -1) (SOLUCIONADO(REVISAR))
//ARREGLAR BUG ADDMIN - AL CREAR UN ENTRENADOR DESDE SALIR TE METE A MENU ADMIN (SOLUCIONADO(REVISAR))
//Acabar relaciones BBDD
//EXCEPCION POR LAS QUERY PARA SACAR LOS TORNEOS ADMIN y por las query (SOLUCIONADO)
//EXCEPCION AL INSERTAR COMBATEENTRENADOR (COMPLETADO)
//COMPROBAR TORNEOS DUPLICADOS (COMPLETADO)
//Error porque no coge los combates de la base de datos (SOLUCIONADO)
//Añadir ganador en el fichero al exportar (COMPLETADO)
//EXPORTAR TORNEOS DEL ENTRENADOR EN SU CARNET Y QUE EL ENTRENADOR ESCOJA SU TORNEO INICIAL (SOLUCIONADO)
//BUG EL PRIMER TORNEO CREA 2 COMBATES PERO APARTIR SIEMPRE 3 (SOLUCIONADO)
//Revisar el exportar torneo a txt porque se añadio lo del ganador del combate (COMPLETADO)
//NOTAS.

//Clase Combate
// - Añadir idEntrenador1 e idEntrenador2 (SOLUCIONADO MEDIANTE COMBATE-ENTRENADOR)

//Clase Sesion
// - Hacer el metodo IniciarSesion() y si no hay cuenta notificar (COMPLETADO)

//Clase Menus
// - Acabar el metodo crearEntrenador() (COMPLETADO)
// - Hacer el metodo menuAdminTorneos() (COMPLETADO)
// - Hacer el metodo menuAdministradorGeneral() (COMPLETADO)

//Clase Funciones
// - Hacer el exportarCarnet() (SEMI-COMPLETADO)
// - Hacer el exportarTorneo() (COMPLETADO)

//Repositorios
// - Hacer el metodo buscarCombatePorIdTorneo() (COMPLETADO)

//Entidades
// - Hacer las relaciones (ManyToMany, ManyToOne...)
// - Da error el mapeo de EntrenadorYCarnet (CORREGIDO)
// - Poner ganador del combate (idEntrenador) (COMPLETADO)
// - Poner ganador del torneo (COMPLETADO)
// - Tabla Torneo-Combate / Torneo-Entrenador

//DUDAS
// HACER SINGLETON PARA LA CONEXION E INTERCAMBIO CON LA BASE DE DATOS (SOLUCIONADO)
