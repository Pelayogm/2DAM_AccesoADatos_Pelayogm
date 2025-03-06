package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.example.Tarea_3_ADT_Pelayogm.Administradores.Admin;
import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Credenciales.CredencialUsuario;
import com.example.Tarea_3_ADT_Pelayogm.Credenciales.Credenciales;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Carnet;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CarnetServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.EntrenadorServiciosImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class Sesion {

    @Autowired
    public Menus menus;
    @Autowired
    public Funciones funciones;
    @Autowired
    CarnetServiciosImplementacion carnetServiciosImplementacion;
    @Autowired
    EntrenadorServiciosImplementacion entrenadorServiciosImplementacion;

    public void IniciarSesion() {
        Scanner entrada = new Scanner(System.in);
        ArrayList<CredencialUsuario> listaContrasenas;

        System.out.println("Iniciar sesión en el sistema.");
        try {
            System.out.println("¿Usuario?");
                String nombreUsuario = entrada.nextLine();
                if (nombreUsuario.isEmpty()) {
                    System.out.println("No se permite este campo vacío.");
                    IniciarSesion();
                }
            System.out.println("¿Contraseña?");
                String contrasenaUsuario = entrada.nextLine();
            if (contrasenaUsuario.isEmpty()) {
                System.out.println("No se permite este campo vacío.");
                IniciarSesion();
            }

            ObjectContainer db = Credenciales.getDb();
            Credenciales.leerFichero(db);
            listaContrasenas = Credenciales.getCredenciales();

               try {
                   if (Credenciales.comprobarCredenciales(nombreUsuario, contrasenaUsuario)) {
                       for (int i = 0; i < listaContrasenas.size(); i++) {
                           if (nombreUsuario.equals(listaContrasenas.get(i).getUsuarioLogin()) && contrasenaUsuario.equals(listaContrasenas.get(i).getContrasenaLogin()) && listaContrasenas.get(i).getRolUsuario().equals("Administrador")) {
                               Admin admin = new Admin(listaContrasenas.get(i).getIdUsuario());
                               menus.menuAdministrador(admin);
                           }

                           if (nombreUsuario.equals(listaContrasenas.get(i).getUsuarioLogin()) && contrasenaUsuario.equals(listaContrasenas.get(i).getContrasenaLogin()) && listaContrasenas.get(i).getRolUsuario().equals("AdministradorTorneos")) {
                               int idAdminTorneos = listaContrasenas.get(i).getIdUsuario();
                               AdminTorneos adminTorneos = new AdminTorneos(nombreUsuario, idAdminTorneos);
                               menus.menuAdminTorneos(adminTorneos);
                           }

                           if (nombreUsuario.equals(listaContrasenas.get(i).getUsuarioLogin()) && contrasenaUsuario.equals(listaContrasenas.get(i).getContrasenaLogin()) && listaContrasenas.get(i).getRolUsuario().equals("Entrenador")) {
                               try {
                                   int idUsuario = listaContrasenas.get(i).getIdUsuario();
                                   Carnet carnet = carnetServiciosImplementacion.obtenerCarnetPorId(idUsuario);
                                   Entrenador entrenador = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(Long.valueOf(idUsuario));
                                   menus.menuEntrenador(entrenador);

                               //Menu Entrenador
                               } catch (Exception e) {
                                   System.out.println("Problemas aquí.");
                               }
                           }
                       }
                   } else {
                       System.out.println("Las credenciales aportadas no están registradas.");
                       System.out.println("¿Quieres registrarte? 1. Sí | 2. No");
                       try {
                           int eleccionUsuario = entrada.nextInt();
                           if (eleccionUsuario == 1) {
                               CrearCuenta();
                           } else {
                               menus.menuInicial();
                           }
                       } catch (Exception e) {
                           System.out.println("La entrada no es correcta. Volviendo al menú inicial...");
                           menus.menuInicial();
                       }
                   }
               } catch (Exception e) {
                   System.out.println("Error interno (Problemas con el fichero de contraseñas).");
               }
        } catch (Exception e) {
            System.out.println("Por favor vuelve a iniciar sesión, los datos introducidos no son validos.");
            IniciarSesion();
        }
    }

    public void CrearCuenta() {
        System.out.println("Registro en el sistema.");
        Scanner entrada = new Scanner(System.in);
        ArrayList<CredencialUsuario> listaContrasenas;

        try {
            System.out.println("¿Nombre de usuario?");
                String nombreUsuario = entrada.nextLine();
            System.out.println("¿Contraseña?");
                String contrasenaUsuario = entrada.nextLine();

            ObjectContainer db = Credenciales.getDb();

            Credenciales.leerFichero(db);
            listaContrasenas = Credenciales.getCredenciales();
            try {
                if (!Credenciales.comprobarCredenciales(nombreUsuario, contrasenaUsuario)) {
                    //Creación del usuario, para el fichero de credenciales.
                    int idEntrenadorNuevo = listaContrasenas.get(listaContrasenas.size() - 1).getIdUsuario() + 1;
                    String rolUsuario = "Entrenador";
                    //Llamada al método para crear un objeto entrenador.
                    menus.crearEntrenador(nombreUsuario, idEntrenadorNuevo);
                    //Registrar al entrenador en el fichero de credenciales.
                    Credenciales.escribirFichero(db,nombreUsuario, contrasenaUsuario,rolUsuario, idEntrenadorNuevo);

                    System.out.println("Entrenador creado con éxito");
                } else {
                    System.out.println("Estas credenciales ya se encuentran registradas en el sistema");
                    System.out.println("¿Desea iniciar sesión? 1. Si | 2. No");
                    try {
                        int eleccionUsuario = entrada.nextInt();
                        if (eleccionUsuario == 1) {
                           IniciarSesion();
                        } else {
                            menus.menuInicial();
                        }
                    } catch (Exception e) {
                        System.out.println("Dato no valido, volviendo al menu incial");
                        menus.menuInicial();
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en la comprobacion de credenciales");
                menus.menuInicial();
            }

        } catch (Exception e) {
            System.out.println("Datos no valido");
            CrearCuenta();
        }
    }

}
