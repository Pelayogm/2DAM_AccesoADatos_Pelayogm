package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.Admin;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;

import java.io.File;
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
                    Menus.menuInicial();
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
            File file;
            System.out.println("¿Desea crear un nuevo Torneo?");
            System.out.println("1. Sí | 2. No");
                int opcionUsuario = entrada.nextInt();
            if (opcionUsuario == 2) {
                Menus.menuAdministrador((Admin) usuario);
            } else {
                try {
                    System.out.println("¿Nombre del torneo?");
                        String nombreTorneo = entrada.next();
                    System.out.println("¿Código de region del torneo?");
                        String regionTorneo = entrada.next();
                        char regionTorneoChar = regionTorneo.charAt(0);
                    System.out.println("¿Usuario del administrador del torneo?");
                        String usuarioAdminTorneos = entrada.next();
                    System.out.println("¿Contraseña del administrador del torneo?");
                        String contrasenaAdminTorneos = entrada.next();
                    System.out.println("¿Los datos introducidos son correctos? 1. Sí | 2. No");
                        try {
                            int confirmacionUsuario = entrada.nextInt();
                            if (confirmacionUsuario == 2) {
                                Funciones.CrearTorneo(usuario);
                            }
                            //FICHERO CREDENCIALES ADMIN TORNEOS

                        } catch (Exception e) {
                            System.out.println("La confirmación no es válida");
                        }
                } catch (Exception e) {
                    System.out.println("Datos no validos");
                    Funciones.CrearTorneo(usuario);
                }
            }
        } else {
            System.out.println("No tienes los permisos para acceder a este menu");
            Funciones.CerrarSesion(usuario);
        }
    }

}
