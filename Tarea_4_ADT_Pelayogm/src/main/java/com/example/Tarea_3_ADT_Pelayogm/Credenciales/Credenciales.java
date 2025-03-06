package com.example.Tarea_3_ADT_Pelayogm.Credenciales;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Credenciales {
    private final static ObjectContainer db = Db4oEmbedded.openFile("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosDelPrograma/Credenciales.db4o");
    private static ArrayList<CredencialUsuario> credenciales = new ArrayList<>();

    public static void leerFichero(ObjectContainer db) {
        credenciales.clear();
        if (!comprobarAdmin(db)) {
            escribirFichero(db, "admin", "admin", "Administrador", 1);
        }

        try {
            Query query = db.query();
            query.constrain(CredencialUsuario.class);
            List<CredencialUsuario> usuarioDisponibles = query.execute();
            if (!usuarioDisponibles.isEmpty()) {
                for (int i = 0; i < usuarioDisponibles.size(); i++) {
                    credenciales.add(usuarioDisponibles.get(i));
                }
            }
        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo para leer");
        }
    }

    public static void escribirFichero (ObjectContainer db, String usuario, String constrasena, String rolUsuario, int idUsuario) {
            CredencialUsuario usuarioARegistrar = new CredencialUsuario(usuario, constrasena, rolUsuario, idUsuario);
            try {
                db.store(usuarioARegistrar);
            } catch (Exception e) {
                System.out.println("No se ha podido guardar el usuario en las credenciales");
            }
    }

    private static boolean comprobarAdmin (ObjectContainer db) {
        Query query = db.query();
        CredencialUsuario admin = new CredencialUsuario("admin", "admin", "Administrador", 1);
        query.constrain(admin);
        List<CredencialUsuario> usuarios = query.execute();
        if (!usuarios.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean comprobarCredenciales (String usuario, String contrasena) {
        if (credenciales.isEmpty()) {
            return false;
        }

        for (int i = 0; i < credenciales.size(); i++) {
            if (credenciales.get(i).getUsuarioLogin().equals(usuario) && credenciales.get(i).getContrasenaLogin().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public static boolean actualizarUsuario(String usuario, String contrasenaAntigua, String rolUsuario ,String nuevaContrasena, int idUsuario) {
        Credenciales.leerFichero(db);
        if (comprobarCredenciales(usuario, contrasenaAntigua)) {
            Query query = db.query();
            CredencialUsuario usuarioQuery = new CredencialUsuario(usuario, contrasenaAntigua, rolUsuario, idUsuario);
            query.constrain(usuarioQuery);
            List<CredencialUsuario> usuarios = query.execute();

            if (!usuarios.isEmpty()) {
                for (int i = 0; i < usuarios.size(); i++) {
                    usuarios.get(i).setContrasenaLogin(nuevaContrasena);
                    db.store(usuarios.get(i));
                }
                return true;
            } else {
                return false;
            }

        } else {
            System.out.println("Contraseña no actualizada");
            return false;
        }
    }

    public static boolean eliminarUsuario(String usuario, String contrasenaAntigua, String rolUsuario, int idUsuario) {
        Credenciales.leerFichero(db);
        if (comprobarCredenciales(usuario, contrasenaAntigua)) {
            Query query = db.query();
            CredencialUsuario usuarioQuery = new CredencialUsuario(usuario, contrasenaAntigua, rolUsuario, idUsuario);
            query.constrain(usuarioQuery);
            List<CredencialUsuario> usuarios = query.execute();

            if (!usuarios.isEmpty()) {
                for (int i = 0; i < usuarios.size(); i++) {
                    db.delete(usuarios.get(i));
                }
                return true;
            } else {
                return false;
            }

        } else {
            System.out.println("Usuario no eliminado");
            return false;
        }
    }

    public static ArrayList<CredencialUsuario> getCredenciales() {
        return credenciales;
    }

    public static ObjectContainer getDb() {
        return db;
    }
}

