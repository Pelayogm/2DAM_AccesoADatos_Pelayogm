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
//Arreglar los ids (COMPLETADO)
//Listar usuarios (COMPLETADO)
//Cambiar contraseña de un usuario (COMPLETADO)
//Mongodb Dependencia (COMPLETADO)
//Saber puntos de un entrenador(COMPLETADO)
//Listar todos los entrenadores y sacar sus puntos (COMPLETADO)
//Los datos ya se meten en mongoDB (COMPLETADO)
//Mostrar informacion de un torneo (COMPLETADO)
//Mostrar ganador (COMPLETADO)
//Mostrar torneos de una region (COMPLETADO)
//Los 2 que más han ganado (COMPLETADO)
