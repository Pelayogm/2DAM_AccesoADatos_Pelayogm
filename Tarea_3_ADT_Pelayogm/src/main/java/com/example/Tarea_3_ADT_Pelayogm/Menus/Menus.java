package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;
import com.example.Tarea_3_ADT_Pelayogm.XML.LectorXML;

import java.util.Scanner;

public class Menus {

    public static void menuInicial() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido Invitado.");
        System.out.println("¿Que desea hacer?");
        System.out.println("1. Iniciar Sesión | 2. Registrarse | 3. Salir ");
        try {
            int opcionUsuario = scanner.nextInt();
            while (opcionUsuario < 4) {
                try {
                    if (opcionUsuario == 1) {
                        Sesion.IniciarSesion();
                    } else if (opcionUsuario == 2) {
                        Sesion.CrearCuenta();
                    } else {
                        System.out.println("Hasta la vista!");
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.out.println("Opción Invalida");
                    Menus.menuInicial();
                }
                System.out.println("1. Iniciar Sesión | 2. Registrarse | 3. Salir ");
                opcionUsuario = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Dato invalido");
            Menus.menuInicial();
        }
    }

    public static Entrenador crearEntrenador(String nombreUsuario, long idUsuario) {
        Scanner entradaEntrenador = new Scanner(System.in);
        System.out.println("Registro en el sistema");
        System.out.println("Nombre elegido: " + nombreUsuario);
        System.out.println("Nacionalidades Admitidas permitidas en el sistema");
        LectorXML.leerXML();
        System.out.println("¿Nacionalidad?");
        String nacionalidadEntrenador = entradaEntrenador.nextLine();

        System.out.println("¿La información es correcta? 1. Sí | 2. No");
        int confirmacionUsuario = entradaEntrenador.nextInt();

        try {
            if (confirmacionUsuario == 2) {
                Menus.crearEntrenador(nombreUsuario, idUsuario);
            } else {
                if (LectorXML.comprobarNacionalidadConXML(nacionalidadEntrenador)){
                    Entrenador entrenador = new Entrenador(idUsuario, nombreUsuario, nacionalidadEntrenador);
                    //Implementar los torneos
                    return entrenador;
                } else {
                    System.out.println("El país introducido no es valido.");
                    Menus.crearEntrenador(nombreUsuario, idUsuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void menuEntrenador(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcionUsuario = scanner.nextInt();
        System.out.println("¿Que desea hacer?");
        System.out.println("1. Unirse a un torneo | 2. Exportar Carnet | 3. Cerrar Sesión ");
        while (opcionUsuario < 4) {
            try {
                if (opcionUsuario == 1) {
                    Sesion.IniciarSesion();
                } else if (opcionUsuario == 2) {
                    Sesion.CrearCuenta();
                } else {
                    System.out.println("Cerrando Sesion");
                    Funciones.CerrarSesion(usuario);
                }
            } catch (Exception e) {
                System.out.println("Opción Invalida");
                Menus.menuInicial();
            }
            System.out.println("1. Iniciar Sesión | 2. Registrarse | 3. Salir ");
            opcionUsuario = scanner.nextInt();
        }

    }

}
