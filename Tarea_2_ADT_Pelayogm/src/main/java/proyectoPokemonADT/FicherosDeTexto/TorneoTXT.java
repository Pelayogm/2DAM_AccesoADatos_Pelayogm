package proyectoPokemonADT.FicherosDeTexto;

import proyectoPokemonADT.DTO.TorneoDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TorneoTXT {

    public static void exportarTorneo (TorneoDTO torneo) throws IOException {
        String nombreTorneo = torneo.getNombre();
        nombreTorneo += ".txt";
        File file = new File("src/main/java/proyectoPokemonADT/ArchivosTorneos/", nombreTorneo);
        if (!file.exists()) {
            file.createNewFile();
            TorneoTXT.escribirFichero(torneo, file);
            System.out.println("Torneo exportado a fichero");
        }
        TorneoTXT.escribirFichero(torneo, file);
        System.out.println("Torneo exportado a fichero");
    }

    public static void escribirFichero (TorneoDTO torneo, File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            try {
                fileWriter.write("Nombre Torneo: " + torneo.getNombre() + "\n");
                fileWriter.write("Region Torneo: " + torneo.getCodRegion() + "\n");
                fileWriter.write("Combates del torneo" + "\n");
                for (int i = 0; i < torneo.getCombatesDelTorneo().size(); i++) {
                    fileWriter.write("Combate " + i + ": " + torneo.getCombatesDelTorneo().get(i).getFecha() + " | " + torneo.getCombatesDelTorneo().get(i).getId() + "\n");
                }
                fileWriter.write("Puntos del torneo: " + torneo.getPuntosVictoria());
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error en la escritura");
            }
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
    }
}
