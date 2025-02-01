package com.example.Tarea_3_ADT_Pelayogm.Menus;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.Admin;
import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Credenciales.Credenciales;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Combate;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.TorneoAdmin;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Usuario;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CombateServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoAdminServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoServiciosImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
    public TorneoAdminServiciosImplementacion torneoAdminServiciosImplementacion;

    public boolean CerrarSesion (Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        if (usuario.isEstadoSesion()) {
            System.out.println("SALIENDO DE LA SESIÓN...");
            try {
                System.out.println("1. Iniciar Sesión | 2. Salir");
                int opcionUsuario = scanner.nextInt();

                if (opcionUsuario == 1) {
                    usuario.setEstadoSesion(false);
                    sesion.IniciarSesion();
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
                    System.out.println("¿Usuario del administrador del torneo?");
                        String usuarioAdminTorneos = entrada.next();
                    System.out.println("¿Contraseña del administrador del torneo?");
                        String contrasenaAdminTorneos = entrada.next();
                    System.out.println("¿Los datos introducidos son correctos? 1. Sí | 2. No");
                    try {
                            int confirmacionUsuario = entrada.nextInt();
                            if (confirmacionUsuario == 2) {
                                CrearTorneo(usuario);
                            }
                            int idAdminTorneos = 0;
                            file = new File("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosDelPrograma", "Credenciales.txt");
                            Credenciales.leerFichero(file);
                            ArrayList<String> listaCredenciales = Credenciales.getCredenciales();

                            //HACER SI NO HAY ADMINISTRADOR-TORNEOS Y CREAR TORNEO
                            if (!Credenciales.comprobarCredenciales(usuarioAdminTorneos, contrasenaAdminTorneos)) {
                                    int ultimoId = Integer.parseInt(listaCredenciales.get(listaCredenciales.size() - 1));
                                    idAdminTorneos = ultimoId + 1;
                                    String rolUsuario = "AdministradorTorneos";
                                    String idUsuarioString = Long.toString(idAdminTorneos);
                                Credenciales.escribirFichero(file, usuarioAdminTorneos, contrasenaAdminTorneos, rolUsuario, idUsuarioString);
                            } else {
                                for (int i = 0; i < listaCredenciales.size(); i++) {
                                    if (listaCredenciales.get(i).equals(usuarioAdminTorneos) && listaCredenciales.get(i + 1).equals(contrasenaAdminTorneos) && listaCredenciales.get(i + 2).equals("AdministradorTorneos")) {
                                        idAdminTorneos = Integer.valueOf(listaCredenciales.get(i + 3));
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
                                idCombate = listaDeCombates.size();
                            }

                            Torneo torneo = new Torneo(idTorneo, nombreTorneo, regionTorneo, 100F, idAdminTorneos);
                            List<Combate> listaCombatesTorneo = new ArrayList<>();
                            torneo.setCombates(listaCombatesTorneo);
                            torneoServiciosImplementacion.insertarTorneo(torneo);

                            for (int i = 1; i < 4; i++) {
                                LocalDate localDate = LocalDate.now();
                                Date fechaCombate = Date.valueOf(localDate);
                                Combate combate = new Combate(idCombate, fechaCombate, torneo);
                                combateServiciosImplementacion.insertarCombate(combate);
                                listaCombatesTorneo.add(combate);
                                idCombate += 1;
                            }

                            torneo.setCombates(listaCombatesTorneo);
                            torneoServiciosImplementacion.insertarTorneo(torneo);
                        System.out.println("Torneo insertado en la base de datos con éxito!");
                            //FICHERO CREDENCIALES ADMIN TORNEOS

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

}
