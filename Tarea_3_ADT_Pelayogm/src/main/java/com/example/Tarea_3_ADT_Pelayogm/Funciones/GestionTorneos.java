package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.*;
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

    public boolean inscribirEntrenador(Usuario usuario) {
        Scanner entrada = new Scanner(System.in);
        List<Torneo> torneosDisponibles = torneoServiciosImplementacion.listaDeTorneosSinGanador();
        List<Entrenador> listaDeEntrenadores = entrenadorServiciosImplementacion.obtenerTodosLosEntrenadores();

        if (!torneosDisponibles.isEmpty()) {
            System.out.println("Torneos disponibles:");
            for (int i = 0; i < torneosDisponibles.size(); i++) {
                System.out.println( i + " - " + "Nombre torneo: " + torneosDisponibles.get(i).getNombreTorneo() + " | Region: " + torneosDisponibles.get(i).getCodigoTorneo());
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
                            } else if (combateEntrenador.getIdEntrenador2() != 0) {
                                Entrenador entrenador2 = entrenadorServiciosImplementacion.obtenerEntrenadorPorId(combateEntrenador.getIdEntrenador1());
                                nombreEntrenador2 = entrenador2.getNombreEntrenador();
                            }
                        System.out.println("Combate " + combate.getIdCombate() + " | " + nombreEntrenador1 + " Contra " + nombreEntrenador2 + " | "
                            + "Fecha: " + combate.getFechaCombate());
                    }
                    if (usuario instanceof AdminTorneos) {
                        System.out.println("Selecciona el combate");
                        try {
                            int opcionUsuarioCombate = entrada.nextInt();


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
                                return true;
                            }
                        }
                        System.out.println("El torneo esta lleno");
                        //Aqui se pone el idGanadorTorneo a -1 para hacer una lista de los torneos que están llenos
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

    public void pelear() {
        List<Entrenador> listaDeEntrenadores = entrenadorServiciosImplementacion.obtenerTodosLosEntrenadores();
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