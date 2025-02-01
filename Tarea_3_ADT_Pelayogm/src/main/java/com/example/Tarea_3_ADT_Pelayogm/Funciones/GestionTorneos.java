package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Entidades.Torneo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class GestionTorneos {

    public void inscribirEntrenador() {

    }

    public void pelear() {

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
                    /*if (!torneo.getCombates().isEmpty()) {
                        for (int i = 0; i < torneo.getCombates().size(); i++) {
                            fileWriter.write("Combate " + i + ": " + torneo.getCombates().get(i).getFechaCombate() + " | " + torneo.getCombates().get(i).getIdCombate() + "\n");
                        }
                    }*/
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