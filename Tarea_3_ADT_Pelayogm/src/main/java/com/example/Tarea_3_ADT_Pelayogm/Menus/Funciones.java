package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;
import java.util.Scanner;

public class Funciones {

    public static boolean CerrarSesion (Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        if (usuario.isEstadoSesion()) {
            System.out.println("SALIENDO DE LA SESIÓN...");
            try {
                System.out.println("1. Iniciar Sesión | 2. Salir");
                int opcionUsuario = scanner.nextInt();

                if (opcionUsuario == 1) {
                    usuario.setEstadoSesion(false);
                    Sesion.IniciarSesion();
                } else {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Error en el cierre de sesión");
            }

        } else {
            System.out.println("No hay ninguna sesión iniciada");
            return false;

        }
        return false;
    }

    public static void CrearTorneo (Usuario usuario) {
        if (usuario.isUsuario()) {
            Scanner entrada = new Scanner(System.in);
            System.out.println("¿Desea crear un nuevo Torneo?");
            System.out.println("1. Sí | 2. No");
                int opcionUsuario = entrada.nextInt();
            if (opcionUsuario == 2) {
                //Menu administrador
            } else {

            }
        } else {
            System.out.println("No tienes los permisos para acceder a este menu");
            Funciones.CerrarSesion(usuario);
        }
    }

}
