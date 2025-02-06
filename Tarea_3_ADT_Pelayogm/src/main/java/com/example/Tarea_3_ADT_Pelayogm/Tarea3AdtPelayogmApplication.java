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
