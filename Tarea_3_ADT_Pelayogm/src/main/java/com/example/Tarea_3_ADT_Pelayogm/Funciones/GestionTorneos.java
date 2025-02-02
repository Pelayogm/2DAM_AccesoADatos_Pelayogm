package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Entrenador;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.EntrenadorServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoServiciosImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class GestionTorneos {

    @Autowired
    TorneoServiciosImplementacion torneoServiciosImplementacion;
    @Autowired
    EntrenadorServiciosImplementacion entrenadorServiciosImplementacion;

    public void inscribirEntrenador() {
        List<Entrenador> listaDeEntrenadores = entrenadorServiciosImplementacion.obtenerTodosLosEntrenadores();
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
                            fileWriter.write("Combate " + i + ": " + torneo.getCombates().get(i).getFechaCombate() + " | " + torneo.getCombates().get(i).getIdCombate() + " | " + "Ganador: " + torneo.getCombates().get(i).getCombateEntrenador().getIdGanador() + "\n");
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