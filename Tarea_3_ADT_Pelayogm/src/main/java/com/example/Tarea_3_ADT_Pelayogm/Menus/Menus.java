package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.Admin;
import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Carnet;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;
import com.example.Tarea_3_ADT_Pelayogm.Funciones.Exportar;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CarnetServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.EntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.XML.LectorXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;

@Component
public class Menus {

    @Autowired
    public Sesion sesion;
    @Autowired
    public Funciones funciones;
    @Autowired
    public Exportar exportar;
    @Autowired
    public EntrenadorServiciosImplementacion entrenadorServiciosImplementacion;
    @Autowired
    public CarnetServiciosImplementacion carnetServiciosImplementacion;

    public void menuInicial() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido Invitado.");
        System.out.println("¿Que desea hacer?");
        System.out.println("1. Iniciar Sesión | 2. Registrarse | 3. Salir ");
        try {
            int opcionUsuario = scanner.nextInt();
            while (opcionUsuario < 4) {
                try {
                    if (opcionUsuario == 1) {
                        sesion.IniciarSesion();
                    } else if (opcionUsuario == 2) {
                        sesion.CrearCuenta();
                    } else {
                        System.out.println("Hasta la vista!");
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.out.println("Opción Invalida");
                    menuInicial();
                }
                System.out.println("1. Iniciar Sesión | 2. Registrarse | 3. Salir ");
                opcionUsuario = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Dato invalido");
            menuInicial();
        }
    }

    public Entrenador crearEntrenador(String nombreUsuario, long idUsuario) {
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
                crearEntrenador(nombreUsuario, idUsuario);
            } else {
                if (LectorXML.comprobarNacionalidadConXML(nacionalidadEntrenador)){
                    Carnet carnet = new Carnet(idUsuario, LocalDate.now(), 0F,0);
                    Entrenador entrenador = new Entrenador(idUsuario, nombreUsuario, nacionalidadEntrenador, carnet);
                    carnetServiciosImplementacion.insertarCarnet(carnet);
                    entrenadorServiciosImplementacion.insertarEntrenador(entrenador);

                    //MOSTRAR TORNEOS DISPONIBLES
                    return entrenador;
                } else {
                    System.out.println("El país introducido no es valido.");
                    crearEntrenador(nombreUsuario, idUsuario);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void menuEntrenador(Entrenador usuario) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido " + usuario.getNombreEntrenador() + "!");
        System.out.println("¿Que quieres hacer?");
        System.out.println("1. Unirse a un torneo | 2. Exportar Carnet | 3. Cerrar Sesión");
        int opcionUsuario = scanner.nextInt();
        while (opcionUsuario < 4) {
            try {
                if (opcionUsuario == 1) {
                    System.out.println("Opción deshabilitada");
                } else if (opcionUsuario == 2) {
                    exportar.ExportarCarnet(usuario);
                } else {
                    System.out.println("Cerrando Sesión");
                    funciones.CerrarSesion(usuario);
                }
            } catch (Exception e) {
                System.out.println("Opción Invalida, cerrando sesión");
                menuInicial();
            }
            System.out.println("1. Unirse a un torneo | 2. Exportar Carnet | 3. Cerrar Sesión");
            opcionUsuario = scanner.nextInt();
        }

    }

    public void menuAdministrador(Admin admin) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Bienvenido Administrador");
        System.out.println("1. Crear un Nuevo Torneo | 2. Cerrar Sesión");
        try {
            int opcionAdmin = entrada.nextInt();
            while (opcionAdmin < 4) {
                switch (opcionAdmin) {
                    case 1: funciones.CrearTorneo(admin);
                    case 2: funciones.CerrarSesion(admin);
                    case 3:
                        System.out.println("Texto de prueba");
                }
                opcionAdmin = entrada.nextInt();
            }

        } catch (Exception e) {
            System.out.println("Dato no valido, vuelva a iniciar sesion");
            sesion.IniciarSesion();
        }

    }

    public void menuAdminTorneos (AdminTorneos adminTorneos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Bienvenido " +  adminTorneos.getNombreAdminTorneo());
        System.out.println("1. Exportar Torneo | 2. Inscribir entrenador | 3. Pelear | 4. Salir");
        try {
            int opcionAdmin = entrada.nextInt();
            while (opcionAdmin < 6) {
                switch (opcionAdmin) {
                    case 1: //Exportar.ExportarTorneo(adminTorneos);
                    case 2: //GestionTorneos.inscribirEntrenador();
                    case 3: //GestionTorneos.pelear();
                    case 4: funciones.CerrarSesion(adminTorneos);
                    case 5:
                        System.out.println("Texto de prueba");
                }
                opcionAdmin = entrada.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Dato no valido, vuelva a iniciar sesion");
            sesion.IniciarSesion();
        }

    }

}
