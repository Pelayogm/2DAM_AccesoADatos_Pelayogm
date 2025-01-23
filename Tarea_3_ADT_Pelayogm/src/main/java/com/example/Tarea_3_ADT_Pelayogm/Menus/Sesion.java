package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Credenciales.Credenciales;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Sesion {

    public static void IniciarSesion() {

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
