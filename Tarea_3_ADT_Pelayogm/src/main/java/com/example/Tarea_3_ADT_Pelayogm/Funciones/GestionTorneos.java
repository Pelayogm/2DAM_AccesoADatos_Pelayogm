package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.*;
import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CarnetServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.CombateEntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.EntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoServiciosImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class GestionTorneos {

    @Autowired
    TorneoServiciosImplementacion torneoServiciosImplementacion;
    @Autowired
    EntrenadorServiciosImplementacion entrenadorServiciosImplementacion;
    @Autowired
    CombateEntrenadorServiciosImplementacion combateEntrenadorServiciosImplementacion;
    @Autowired
    Menus menus;
    @Autowired
    CarnetServiciosImplementacion carnetServiciosImplementacion;

    public boolean inscribirEntrenador(Usuario usuario) {
        Scanner entrada = new Scanner(System.in);
        List<Torneo> torneosDisponibles = torneoServiciosImplementacion.listaDeTorneosSinGanador();
        List<Entrenador> listaDeEntrenadores = entrenadorServiciosImplementacion.obtenerTodosLosEntrenadores();
        //Contador para en caso de que no haya torneos disponibles darle una opción al usuario para salir.
        int contador = 0;

        if (!torneosDisponibles.isEmpty()) {
            System.out.println("Torneos disponibles:");
            if (usuario instanceof AdminTorneos) {
                for (int i = 0; i < torneosDisponibles.size(); i++) {
                    if (torneosDisponibles.get(i).getIdAdminTorneo() == usuario.getIdUsuarioInterfaz()) {
                        System.out.println( i + " - " + "Nombre torneo: " + torneosDisponibles.get(i).getNombreTorneo() + " | Region: " + torneosDisponibles.get(i).getCodigoTorneo());
                        contador++;
                    }
                }
            } else {
                for (int i = 0; i < torneosDisponibles.size(); i++) {
                    System.out.println( i + " - " + "Nombre torneo: " + torneosDisponibles.get(i).getNombreTorneo() + " | Region: " + torneosDisponibles.get(i).getCodigoTorneo());
                    contador++;
                }
            }

            if (contador == 0) {
                System.out.println("No hay torneos disponibles");
                System.out.println("Volviendo...");
                if (usuario instanceof AdminTorneos) {
                    menus.menuAdminTorneos((AdminTorneos) usuario);
                } else {
                    menus.menuInicial();
                }
            }

            System.out.println("Seleccione un torneo.");
            try {
                int opcionUsuario = entrada.nextInt();
                try {
                    System.out.println("Torneo escogido: " + torneosDisponibles.get(opcionUsuario).getNombreTorneo());
                    List<Combate> combatesDelTorneo = torneosDisponibles.get(opcionUsuario).getCombates();
                    for (int i = 0; i < combatesDelTorneo.size(); i++) {
                        CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                        Combate combate = combatesDelTorneo.get(i);
                        String nombreEntrenador1 = "plazaLibre";
                        String nombreEntrenador2 = "plazaLibre";
                            if (combateEntrenador.getIdEntrenador1() != 0) {
                                Entrenador entrenador1 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador1());
                                nombreEntrenador1 = entrenador1.getNombreEntrenador();
                            }

                            if (combateEntrenador.getIdEntrenador2() != 0) {
                                Entrenador entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador2());
                                nombreEntrenador2 = entrenador2.getNombreEntrenador();
                            }
                        System.out.println("Combate " + combate.getIdCombate() + " | " + nombreEntrenador1 + " Contra " + nombreEntrenador2 + " | "
                            + "Fecha: " + combate.getFechaCombate());
                    }
                    if (usuario instanceof AdminTorneos) {
                        System.out.println("Selecciona el primer o segundo combate.");
                        try {
                            int opcionUsuarioCombate = entrada.nextInt();
                            //Hacer lista de entrenadores que no estén inscritos en el torneo


                        } catch (Exception e) {
                            System.out.println("No se ha podido escoger el combate");
                            inscribirEntrenador(usuario);
                        }
                    } else {
                        for (int i = 0; i < combatesDelTorneo.size() - 1; i++) {
                            CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                            if (combateEntrenador.getIdEntrenador1() == 0) {
                                combateEntrenador.setIdEntrenador1(usuario.getIdUsuarioInterfaz());
                                combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                System.out.println("Inscrito con éxito");
                                return true;
                            } else if (combateEntrenador.getIdEntrenador2() == 0) {
                                combateEntrenador.setIdEntrenador2(usuario.getIdUsuarioInterfaz());
                                combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenador);
                                System.out.println("Inscrito con éxito");
                                // Si es el último combate, se marca el torneo como lleno
                                if (i == combatesDelTorneo.size() - 2) {
                                    Torneo torneo = torneoServiciosImplementacion.obtenerTorneoPorId(
                                            combatesDelTorneo.get(i).getTorneo().getIdTorneo());
                                    torneo.setIdGanador(-1);
                                    torneoServiciosImplementacion.insertarTorneo(torneo);
                                }
                                return true;
                            }
                        }
                        System.out.println("El torneo esta lleno");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("Opción fuera de límites");
                    inscribirEntrenador(usuario);
                }
            } catch (Exception e) {
                System.out.println("Entrada no reconocida, vuelve al menu");
                inscribirEntrenador(usuario);
            }
        } else {
            System.out.println("Actualmente no hay ningún torneo disponible para inscribir a un entrenador");
        }
        return false;
    }

    public void pelear(Usuario usuario) {
        //Los torneos listos para combatir son los que su idGanador es -1
        List<Torneo> torneosDisponibles = torneoServiciosImplementacion.listaDeTorneosParaCombatir();
        //Este boolean sirve para saber si es el combate final de un torneo
        boolean combateFinalBoolean = false;
        Scanner entrada = new Scanner(System.in);

        if (!torneosDisponibles.isEmpty()) {
            System.out.println("Lista de torneos:");
            for (int i = 0; i < torneosDisponibles.size(); i++) {
                Torneo torneo = torneosDisponibles.get(i);
                if (torneo.getIdAdminTorneo() == usuario.getIdUsuarioInterfaz()) {
                    System.out.println(i + " - " + torneo.getNombreTorneo() + " está listo.");
                    System.out.println("---------------------------------------------------------");
                }
            }
            System.out.println("Selecciona el torneo.");
            try {
                int opcionUsuario = entrada.nextInt();
                Torneo torneoSeleccionado = torneosDisponibles.get(opcionUsuario);
                List<Combate> combatesDelTorneo = torneoSeleccionado.getCombates();
                Entrenador entrenador1;
                Entrenador entrenador2;
                    for (int i = 0; i < combatesDelTorneo.size(); i++) {
                        CombateEntrenador combateEntrenador = combatesDelTorneo.get(i).getCombateEntrenador();
                        if (combateEntrenador.getIdGanador() == 0) {
                            if (combateEntrenador.getIdEntrenador1() != 0 && combateEntrenador.getIdEntrenador2() != 0) {
                                entrenador1 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador1());
                                entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador2());
                                System.out.println(i + " - " + entrenador1.getNombreEntrenador() + " CONTRA " + entrenador2.getNombreEntrenador());
                            }
                        }
                    }
                    //Se escoge el combate y los datos se pasan a las variables
                System.out.println("Escoge el combate...");
                    int opcionUsuarioCombate = entrada.nextInt();
                    CombateEntrenador combateEntrenadorEscogido = combatesDelTorneo.get(opcionUsuarioCombate).getCombateEntrenador();
                    entrenador1 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenadorEscogido.getIdEntrenador1());
                    entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenadorEscogido.getIdEntrenador2());
                    //Si es el último combate de la lista entonces es el combate final, activamos el booleano para activar el menú.
                    if (combateEntrenadorEscogido.getIdCombateEntrenador() == combatesDelTorneo.get(combatesDelTorneo.size() - 1).getCombateEntrenador().getIdCombateEntrenador()) {
                        System.out.println("- COMBATE FINAL -");
                        combateFinalBoolean = true;
                    }
                    //Se pregunta al administrador quien es el ganador.
                System.out.println("¿Quien gana? 1. " + entrenador1.getNombreEntrenador() + " O 2. " + entrenador2.getNombreEntrenador());
                    try {
                        int entrenadorGanadorScanner = entrada.nextInt();
                        int idGanador;
                        if (entrenadorGanadorScanner <= 1) {
                            System.out.println("Ha ganado " + entrenador1.getNombreEntrenador());
                            idGanador = entrenador1.getIdEntrenador().intValue();
                        } else {
                            System.out.println("Ha ganado " + entrenador2.getNombreEntrenador());
                            idGanador = entrenador2.getIdEntrenador().intValue();
                        }
                        //El ganador del combate se establece en el objeto CombateEntrenador
                        combateEntrenadorEscogido.setIdGanador(idGanador);
                        //Se inserta en la base de datos para actualizar los registros
                        combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateEntrenadorEscogido);
                        if (combateFinalBoolean) {
                            //Si es el último combate se busca el combate final
                            Torneo torneo = torneoServiciosImplementacion.obtenerTorneoPorId(torneoSeleccionado.getIdTorneo());
                            torneo.setIdGanador(idGanador);
                            //Se inserta el torneo para actualizar el torneo
                            torneoServiciosImplementacion.insertarTorneo(torneo);
                        }

                        Entrenador entrenadorGanador = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(idGanador);

                        //Esto sirve para saber si es el último combate y evitar
                        if (!combateFinalBoolean) {
                            CombateEntrenador combateFinal = combatesDelTorneo.get(combatesDelTorneo.size() - 1).getCombateEntrenador();
                            if (combateFinal.getIdEntrenador1() == 0) {
                                combateFinal.setIdEntrenador1(idGanador);
                                System.out.println(entrenadorGanador.getNombreEntrenador() + " pasa al combate final");
                            } else if (combateFinal.getIdEntrenador2() == 0) {
                                combateFinal.setIdEntrenador2(idGanador);
                                System.out.println(entrenadorGanador.getNombreEntrenador() + " pasa al combate final");
                            }
                            combateEntrenadorServiciosImplementacion.insertarCombateEntrenador(combateFinal);
                        }

                        if (combateFinalBoolean) {
                            System.out.println(entrenadorGanador.getNombreEntrenador() + " es el ganador del torneo " + torneoSeleccionado.getNombreTorneo());
                            Carnet carnetEntrenadorGanador = carnetServiciosImplementacion.obtenerCarnetPorId(entrenadorGanador.getIdEntrenador());
                            carnetEntrenadorGanador.setPuntosCarnet((float) (carnetEntrenadorGanador.getPuntosCarnet() + torneoSeleccionado.getPuntosVictoriaTorneo()));
                            carnetEntrenadorGanador.setNumeroVictorias(carnetEntrenadorGanador.getNumeroVictorias() + 1);
                            carnetServiciosImplementacion.insertarCarnet(carnetEntrenadorGanador);
                        }

                        System.out.println("¿Quieres continuar? 1. Si | 2. No");
                        try {
                            int confirmacion = entrada.nextInt();
                            if (confirmacion == 1) {
                                pelear(usuario);
                            } else {
                                menus.menuAdminTorneos((AdminTorneos) usuario);
                            }
                        } catch (Exception e) {
                            System.out.println("Volviendo al menú");
                            menus.menuAdminTorneos((AdminTorneos) usuario);
                        }
                    } catch (Exception e) {
                        System.out.println("Entrada no reconocida, volviendo...");
                        pelear(usuario);
                    }
            } catch (Exception e) {
                System.out.println("Dato no reconocido, volviendo...");
                pelear(usuario);
            }
        } else {
            System.out.println("No hay torneos disponibles para realizar combates.");
            System.out.println("Volviendo al menú...");
            menus.menuAdminTorneos((AdminTorneos) usuario);
        }
    }

    public void crearFichero(Torneo torneo) {
        String nombreTorneo = torneo.getNombreTorneo();
        nombreTorneo += ".txt";
        File file = new File("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosSalida/", nombreTorneo);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    escribirFichero(torneo, file);
                    System.out.println("Torneo exportado a fichero.");
                } catch (Exception e) {
                    System.out.println("No se ha creado el archivo txt.");
                }
            } else {
                escribirFichero(torneo, file);
                System.out.println("Fichero del torneo actualizado.");
            }
    }

    private void escribirFichero(Torneo torneo, File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            try {
                fileWriter.write("Nombre Torneo: " + torneo.getNombreTorneo() + "\n");
                fileWriter.write("Region Torneo: " + torneo.getCodigoTorneo() + "\n");
                fileWriter.write("Combates del torneo" + "\n");
                    if (!torneo.getCombates().isEmpty()) {
                        for (int i = 0; i < torneo.getCombates().size(); i++) {
                            fileWriter.write("Combate " + i + ": " + torneo.getCombates().get(i).getFechaCombate() + " | ID: " + torneo.getCombates().get(i).getIdCombate() + " | " + "Ganador: " + torneo.getCombates().get(i).getCombateEntrenador().getIdGanador() + "\n");
                        }
                    }
                fileWriter.write("Puntos del torneo: " + torneo.getPuntosVictoriaTorneo());
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error en la escritura");
            }
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        }
    }