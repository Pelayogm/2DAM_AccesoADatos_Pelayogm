package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.example.Tarea_3_ADT_Pelayogm.Administradores.Admin;
import com.example.Tarea_3_ADT_Pelayogm.Credenciales.CredencialUsuario;
import com.example.Tarea_3_ADT_Pelayogm.Credenciales.Credenciales;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.*;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CombateEntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CombateServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.EntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoServiciosImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Funciones {

    @Autowired
    public Menus menus;
    @Autowired
    public Sesion sesion;
    @Autowired
    public TorneoServiciosImplementacion torneoServiciosImplementacion;
    @Autowired
    public CombateServiciosImplementacion combateServiciosImplementacion;
    @Autowired
    public CombateEntrenadorServiciosImplementacion combateEntrenadorServiciosImplementacion;
    @Autowired
    public EntrenadorServiciosImplementacion entrenadorServiciosImplementacion;

    public boolean CerrarSesion (Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        if (usuario.isEstadoSesion()) {
            System.out.println("SALIENDO DE LA SESIÓN...");
            try {
                System.out.println("1. Menú inicial | 2. Salir");
                int opcionUsuario = scanner.nextInt();

                if (opcionUsuario == 1) {
                    usuario.setEstadoSesion(false);
                    menus.menuInicial();
                } else {
                    System.exit(0);
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

    public void CrearTorneo (Usuario usuario) {
        List<Torneo> listaDeTorneos = torneoServiciosImplementacion.obtenerTodosLosTorneos();
        List<Combate> listaDeCombates = combateServiciosImplementacion.obtenerTodosLosCombates();

        if (usuario.isUsuario()) {
            Scanner entrada = new Scanner(System.in);
            File file;
            System.out.println("¿Desea crear un nuevo Torneo?");
            System.out.println("1. Sí | 2. No");
                int opcionUsuario = entrada.nextInt();
            if (opcionUsuario == 2) {
                menus.menuAdministrador((Admin) usuario);
            } else {
                try {
                    System.out.println("¿Nombre del torneo?");
                        String nombreTorneo = entrada.next();
                    System.out.println("¿Código de region del torneo?");
                        String regionTorneo = entrada.next();
                        char regionTorneoChar = regionTorneo.charAt(0);
                    System.out.println("¿Cuántos puntos de victoria da el torneo?");
                        Float puntosVictoria = entrada.nextFloat();
                    System.out.println("¿Usuario del administrador del torneo?");
                        String usuarioAdminTorneos = entrada.next();
                    System.out.println("¿Contraseña del administrador del torneo?");
                        String contrasenaAdminTorneos = entrada.next();
                    System.out.println("¿Los datos introducidos son correctos? 1. Sí | 2. No");
                    try {
                            int confirmacionUsuario = entrada.nextInt();
                            if (confirmacionUsuario == 2) {
                                menus.menuAdministrador((Admin) usuario);
                            }
                            int idAdminTorneos = 0;
                            ObjectContainer db = Credenciales.getDb();

                            Credenciales.leerFichero(db);
                            ArrayList<CredencialUsuario> listaCredenciales = Credenciales.getCredenciales();

                            //HACER SI NO HAY ADMINISTRADOR-TORNEOS Y CREAR TORNEO
                            if (!Credenciales.comprobarCredenciales(usuarioAdminTorneos, contrasenaAdminTorneos)) {
                                    idAdminTorneos = listaCredenciales.get(listaCredenciales.size() - 1).getIdUsuario() + 1;
                                    String rolUsuario = "AdministradorTorneos";
                                Credenciales.escribirFichero(db, usuarioAdminTorneos, contrasenaAdminTorneos, rolUsuario, idAdminTorneos);
                            } else {
                                for (int i = 0; i < listaCredenciales.size(); i++) {
                                    if (listaCredenciales.get(i).equals(usuarioAdminTorneos) && listaCredenciales.get(i + 1).equals(contrasenaAdminTorneos) && listaCredenciales.get(i + 2).equals("AdministradorTorneos")) {
                                        idAdminTorneos = listaCredenciales.get(i).getIdUsuario();
                                    }
                                }
                            }

                            int idTorneo;
                            int idCombate;

                            if (listaDeTorneos.isEmpty()) {
                                idTorneo = 1;
                            } else {
                                idTorneo = listaDeTorneos.size() + 1;
                            }

                            if (listaDeCombates.isEmpty()) {
                                idCombate = 1;
                            } else {
                                idCombate = listaDeCombates.size() + 1;
                            }
                            //Creamos el torneo para comparar con los que hay en la base de datos
                            Torneo torneo = new Torneo(idTorneo, nombreTorneo, regionTorneo, puntosVictoria, idAdminTorneos, 0);
                            boolean validado = false;
                            //Si la lista no está vacía significa que hay torneos con los que podemos comparar y se usa validado para asegurarse de que solo se hace una vez
                            if (!listaDeTorneos.isEmpty() && !validado) {
                                for (int i = 0; i < listaDeTorneos.size(); i++) {
                                    Torneo torneoActual = listaDeTorneos.get(i);
                                    //Si ya hay un torneo que se llame igual te manda crear otro.
                                    if (torneoActual.getNombreTorneo().equals(torneo.getNombreTorneo())) {
                                        System.out.println("No puede haber 2 torneos que se llamen igual.");
                                        System.out.println("Volviendo al menú de creación de torneo...");
                                        CrearTorneo(usuario);
                                    //Si no se llaman igual pero están en la misma región te manda crear otro igualmente.
                                    } else if (torneoActual.getCodigoTorneo().equals(torneo.getCodigoTorneo())) {
                                        System.out.println("Ya existe un torneo en esta región.");
                                        System.out.println("Volviendo al menú de creación de torneo...");
                                        CrearTorneo(usuario);
                                    }
                                }
                                System.out.println("Datos del torneo validados.");
                                validado = true;
                            }

                            List<Combate> listaCombatesTorneo = new ArrayList<>();
                            torneo.setCombates(listaCombatesTorneo);
                            torneoServiciosImplementacion.insertarTorneo(torneo);

                            for (int i = 1; i < 4; i++) {
                                LocalDate localDate = LocalDate.now();
                                Date fechaCombate = Date.valueOf(localDate);
                                Combate combate = new Combate(idCombate, fechaCombate, torneo, null);
                                CombateEntrenador combateEntrenador = new CombateEntrenador(idCombate, combate, 0, 0, 0);
                                combate.setCombateEntrenador(combateEntrenador);
                                combateServiciosImplementacion.insertarCombate(combate);
                                combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                listaCombatesTorneo.add(combate);
                                idCombate += 1;
                            }

                            torneo.setCombates(listaCombatesTorneo);
                            torneoServiciosImplementacion.insertarTorneo(torneo);
                        System.out.println("Torneo insertado en la base de datos con éxito!");

                        } catch (Exception e) {
                            System.out.println("La confirmación no es válida");
                        }
                } catch (Exception e) {
                    System.out.println("Datos no validos");
                    CrearTorneo(usuario);
                }
            }
        } else {
            System.out.println("No tienes los permisos para acceder a este menu");
            CerrarSesion(usuario);
        }
    }

    public void consultarDatosDeUnTorneo(Usuario usuario) {
        if (usuario.isUsuario()) {
            Scanner entrada = new Scanner(System.in);
            System.out.println("1. Mostrar información de un torneo | 2. Saber ganador de un torneo | 3. Los 2 entrenadores que mas ganaron | 4. Listar entrenadores con sus puntos" +
                    "| 5. Saber puntos de un entrenador | 6. Saber los torneos de una region | 7. Salir");
            try {
                int opcionUsuario = entrada.nextInt();
                while (opcionUsuario < 8) {
                    switch (opcionUsuario) {
                        case 1: {}
                        case 2: {}
                        case 3: {}
                        case 4: {listarEntrenadoresConPuntos(); break;}
                        case 5: {saberPuntosDeUnEntrenador(); break;}
                        case 6: {}
                        case 7: {menus.menuAdministrador((Admin) usuario); break;}
                    }
                    System.out.println("1. Mostrar informacion de un torneo | 2. Saber ganador de un torneo | 3. Los 2 entrenadores que mas ganaron | 4. Listar entrenadores con sus puntos" +
                            " | 5. Saber puntos de un entrenador | 6. Saber los torneos de una region | 7. Salir");
                    opcionUsuario = entrada.nextInt();
                }

            } catch (Exception e) {
                System.out.println("Entrada no reconocida. Volviendo al menu");
                menus.menuAdministrador((Admin) usuario);
            }
        }
    }

    public void listarEntrenadoresConPuntos() {
        List<Entrenador> entrenadores = entrenadorServiciosImplementacion.obtenerTodosLosEntrenadores();
        if (!entrenadores.isEmpty()) {
            for (int i = 0; i < entrenadores.size(); i++) {
                System.out.println("Entrenador: " + entrenadores.get(i).getNombreEntrenador() +  " | Puntos: " + entrenadores.get(i).getCarnetEntrenador().getPuntosCarnet());
            }
        } else {
            System.out.println("No hay entrenadores");
        }
    }

    public void saberPuntosDeUnEntrenador() {
        List<Entrenador> entrenadores = entrenadorServiciosImplementacion.obtenerTodosLosEntrenadores();
        Scanner entrada = new Scanner(System.in);
        if (!entrenadores.isEmpty()) {
                for (int i = 0; i < entrenadores.size(); i++) {
                System.out.println( i +" - Entrenador: " + entrenadores.get(i).getNombreEntrenador());
            }
            System.out.println("Elige un entrenador");
                try {
                    int opcionUsuario = entrada.nextInt();
                    if (opcionUsuario >= entrenadores.size()) {
                        System.out.println("Fuera de límites, volviendo a empezar...");
                        saberPuntosDeUnEntrenador();
                    } else {
                        Entrenador entrenador = entrenadores.get(opcionUsuario);
                        System.out.println(entrenador.getNombreEntrenador() + " tiene " + entrenador.getCarnetEntrenador().getPuntosCarnet());
                    }

                } catch (Exception e) {
                    System.out.println("Entrada no reconocida");
                    saberPuntosDeUnEntrenador();
                }
        } else {
            System.out.println("No hay entrenadores");
        }

    }



    public void gestionarUsuarios(Usuario usuario) {
        if (usuario.isUsuario()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Listar usuarios | 2. Eliminar credenciales de un usuario | 3. Modificar credenciales | 4. Salir");
            try {
                int entrada = scanner.nextInt();
                while (entrada < 5) {
                    switch (entrada) {
                        case 1: {listarUsuarios(); break;}
                        case 2: {eliminarCredenciales(usuario); break;}
                        case 3: {actualizarCredenciales(usuario); break;}
                        case 4: {menus.menuAdministrador((Admin) usuario); break;}
                    }
                    System.out.println("1. Listar usuarios | 2. Eliminar credenciales de un usuario | 3. Modificar credenciales | 4. Salir");
                    entrada = scanner.nextInt();
                }

            } catch (Exception e) {
                System.out.println("Entrada no reconocida, volviendo...");
                menus.menuAdministrador((Admin) usuario);
            }
        }
    }

    public void listarUsuarios() {
        //Cargamos la bd de usuarios
        ObjectContainer db = Credenciales.getDb();
        Credenciales.leerFichero(db);
        //Al leer el fichero de la bd, creamos un arraylist con los datos actualizados
        ArrayList<CredencialUsuario> listaCredenciales = Credenciales.getCredenciales();
        System.out.println("Administradores");
        for (int i = 0; i < listaCredenciales.size(); i++) {
            if (listaCredenciales.get(i).getRolUsuario().equals("AdministradorTorneos")) {
                System.out.println("-------------------------------");
                CredencialUsuario credencialActual = listaCredenciales.get(i);
                System.out.println("Usuario: " + credencialActual.getUsuarioLogin() + " | Contraseña: " + credencialActual.getContrasenaLogin());
                System.out.println("-------------------------------");
            }

        }
        System.out.println("Entrenadores");
        for (int i = 0; i < listaCredenciales.size(); i++) {
            if (listaCredenciales.get(i).getRolUsuario().equals("Entrenador")) {
                System.out.println("-------------------------------");
                CredencialUsuario credencialActual = listaCredenciales.get(i);
                System.out.println("Usuario: " + credencialActual.getUsuarioLogin() + " | Contraseña: " + credencialActual.getContrasenaLogin());
                System.out.println("-------------------------------");
            }
        }


    }

    public void eliminarCredenciales(Usuario usuario) {
        //Cargamos la bd de usuarios
        ObjectContainer db = Credenciales.getDb();
        Credenciales.leerFichero(db);
        //Al leer el fichero de la bd, creamos un arraylist con los datos actualizados
        ArrayList<CredencialUsuario> listaCredenciales = Credenciales.getCredenciales();
        //Creamos el scanner
        Scanner scanner = new Scanner(System.in);
        if (!listaCredenciales.isEmpty()) {
            for (int i = 0; i < listaCredenciales.size(); i++) {
                if (!listaCredenciales.get(i).getRolUsuario().equals("Administrador")) {
                    System.out.println(i + " Usuario: " + listaCredenciales.get(i).getUsuarioLogin());
                }
            }
        }
            System.out.println("Escoge el usuario");
            try {
                int opcionUsuario = scanner.nextInt();
                if (opcionUsuario >= listaCredenciales.size()) {
                    System.out.println("Fuera de limites");
                    eliminarCredenciales(usuario);
                } else {
                    System.out.println("Usuario seleccionado: " + listaCredenciales.get(opcionUsuario).getUsuarioLogin());
                    System.out.println("¿Quieres eliminar al usuario? 1. Si | 2. No");
                    try {
                        int eleccionUsuario = scanner.nextInt();
                        if (eleccionUsuario == 1) {
                            CredencialUsuario actual = listaCredenciales.get(opcionUsuario);
                            if (Credenciales.eliminarUsuario(actual.getUsuarioLogin(), actual.getContrasenaLogin(), actual.getRolUsuario(), actual.getIdUsuario())) {
                                System.out.println(actual.getUsuarioLogin() + " eliminado del sistema");
                            } else {
                                System.out.println(actual.getUsuarioLogin() + " no se ha eliminado del sistema");
                            }
                        } else {
                            System.out.println("Entendido, el usuario no se eliminara");
                        }
                    } catch (Exception e) {
                        System.out.println("VOLVIENDO...");
                        eliminarCredenciales(usuario);
                    }
                }
            } catch (Exception e) {
                System.out.println("Opcion fuera de limites");
                eliminarCredenciales(usuario);
            }

    }

    public void actualizarCredenciales(Usuario usuario) {
        //Cargamos la bd de usuarios
        ObjectContainer db = Credenciales.getDb();
        Credenciales.leerFichero(db);
        //Al leer el fichero de la bd, creamos un arraylist con los datos actualizados
        ArrayList<CredencialUsuario> listaCredenciales = Credenciales.getCredenciales();
        //Creamos el scanner
        Scanner scanner = new Scanner(System.in);

        if (!listaCredenciales.isEmpty()) {
            for (int i = 0; i < listaCredenciales.size(); i++) {
                if (!listaCredenciales.get(i).getRolUsuario().equals("Administrador")) {
                    System.out.println(i + " Usuario: " + listaCredenciales.get(i).getUsuarioLogin());
                }
            }
            System.out.println("Escoge el usuario");
            try {
                int opcionUsuario = scanner.nextInt();
                if (opcionUsuario >= listaCredenciales.size()) {
                    System.out.println("Fuera de limites");
                    actualizarCredenciales(usuario);
                } else {
                    System.out.println("Usuario seleccionado: " + listaCredenciales.get(opcionUsuario).getUsuarioLogin());
                    try {
                        System.out.println("Introduzca la nueva contraseña: ");
                        String a = scanner.nextLine();
                        String nuevaPass = scanner.nextLine();
                        System.out.println("¿Es correcta? 1. Sí | 2. No");
                        try {
                            int confirmacion = scanner.nextInt();
                            if (confirmacion == 1) {
                                CredencialUsuario actual = listaCredenciales.get(opcionUsuario);
                                if (Credenciales.actualizarUsuario(actual.usuarioLogin, actual.contrasenaLogin, actual.rolUsuario, nuevaPass, actual.getIdUsuario())) {
                                    System.out.println("CONTRASEÑA ACTUALIZADA");
                                    gestionarUsuarios(usuario);
                                } else {
                                    System.out.println("NO SE HA ACTUALIZADO LA CONTRASEÑA");
                                }
                            } else {
                                System.out.println("CANCELADO");
                                actualizarCredenciales(usuario);
                            }
                        } catch (Exception e) {
                            System.out.println("Volviendo...");
                            actualizarCredenciales(usuario);
                        }
                    } catch (Exception e) {
                        System.out.println("Dato no valido");
                        actualizarCredenciales(usuario);
                    }
                }

            } catch (Exception e) {
                System.out.println("Dato no válido, volviendo...");
                menus.menuAdministrador((Admin) usuario);

            }
        }
    }

}
