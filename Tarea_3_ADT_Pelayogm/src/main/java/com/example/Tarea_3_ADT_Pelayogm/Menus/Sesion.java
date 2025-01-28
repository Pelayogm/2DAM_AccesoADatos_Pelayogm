package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.Admin;
import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Credenciales.Credenciales;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Sesion {

    public static void IniciarSesion() {
        Scanner entrada = new Scanner(System.in);
        ArrayList<String> listaContrasenas = new ArrayList<>();
        File file;

        System.out.println("Iniciar sesión en el sistema");
        try {
            System.out.println("¿Usuario?");
                String nombreUsuario = entrada.nextLine();
                if (nombreUsuario.isEmpty()) {
                    System.out.println("No se permite este campo vacío.");
                    Sesion.IniciarSesion();
                }
            System.out.println("¿Contraseña?");
                String contrasenaUsuario = entrada.nextLine();
            if (contrasenaUsuario.isEmpty()) {
                System.out.println("No se permite este campo vacío.");
                Sesion.IniciarSesion();
            }

            file = new File("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosDelPrograma", "Credenciales.txt");
            Credenciales.leerFichero(file);
            listaContrasenas = Credenciales.getCredenciales();
               try {
                   if (Credenciales.comprobarCredenciales(nombreUsuario, contrasenaUsuario)) {
                       for (int i = 0; i < listaContrasenas.size(); i++) {
                           if (nombreUsuario.equals(listaContrasenas.get(i)) && contrasenaUsuario.equals(listaContrasenas.get(i + 1)) && listaContrasenas.get(i + 2).equals("Administrador")) {
                               Admin admin = new Admin(1);
                               Menus.menuAdministrador(admin);
                               //Menu admin
                           }

                           if (nombreUsuario.equals(listaContrasenas.get(i)) && contrasenaUsuario.equals(listaContrasenas.get(i + 1)) && listaContrasenas.get(i + 2).equals("AdministradorTorneos")) {
                               int idAdminTorneos = Integer.parseInt(listaContrasenas.get(i + 3));
                               AdminTorneos adminTorneos = new AdminTorneos(idAdminTorneos);
                               Menus.menuEntrenador(adminTorneos);
                               //Menu adminTorneos
                           }

                           if (nombreUsuario.equals(listaContrasenas.get(i)) && contrasenaUsuario.equals(listaContrasenas.get(i + 1)) && listaContrasenas.get(i + 2).equals("Entrenador")) {
                               try {
                                   int idUsuario = Integer.parseInt(listaContrasenas.get(i + 3));
                                   Menus.menuInicial();
                                   //Menu Entrenador
                               } catch (Exception e) {
                                   System.out.println("Problemas aqui");
                               }
                           }
                       }
                   }
               } catch (Exception e) {
                   System.out.println("Error interno (Problemas con el fichero de contraseñas)");
               }


        } catch (Exception e) {
            System.out.println("Por favor vuelve a iniciar sesión, los datos introducidos no son validos");
            Sesion.IniciarSesion();
        }
    }

    public static void CrearCuenta() {
        System.out.println("Registro en el sistema");
        Scanner entrada = new Scanner(System.in);
        ArrayList<String> listaContrasenas = new ArrayList<>();
        File file;

        try {
            System.out.println("¿Nombre de usuario?");
                String nombreUsuario = entrada.nextLine();
            System.out.println("¿Contraseña?");
                String contrasenaUsuario = entrada.nextLine();

            file = new File("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosDelPrograma", "Credenciales.txt");
            Credenciales.leerFichero(file);
            listaContrasenas = Credenciales.getCredenciales();
            try {
                if (!Credenciales.comprobarCredenciales(nombreUsuario, contrasenaUsuario)) {
                    int ultimoId = Integer.parseInt(listaContrasenas.get(listaContrasenas.size() - 1));
                    long idUsuario = ultimoId + 1;
                    String rolUsuario = "Entrenador";
                    String idUsuarioString = Long.toString(idUsuario);
                    Menus.crearEntrenador(nombreUsuario, idUsuario);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
