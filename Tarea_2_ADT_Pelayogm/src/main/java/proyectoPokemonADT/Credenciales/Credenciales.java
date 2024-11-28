package proyectoPokemonADT.Credenciales;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Credenciales {
    private static ArrayList<String> credenciales = new ArrayList<>();
    private static int contadorLineas = 0;

    public static void leerFichero(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String linea = bufferedReader.readLine();
                while (linea != null) {
                    contadorLineas++;
                    String [] lineaActual = linea.split(" ");
                    credenciales.addAll(Arrays.asList(lineaActual));
                    linea = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                System.out.println("Fin del fichero");
            }

        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo para leer");
        }
    }

    public static void escribirFichero (File file, String datosParaEscribir, String rolUsuario, String idUsuario) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            try {
                fileWriter.write(datosParaEscribir + " ");
                fileWriter.write(rolUsuario + " ");
                fileWriter.write(idUsuario + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se ha podido escribir");
            }
        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo para escribir");
        }
    }

    public static boolean comprobarCredenciales (String credencialesDelUsuario) {
        for (int i = 0; i < credenciales.size(); i++) {
            if (credenciales.get(i).equals(credencialesDelUsuario)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getCredenciales() {
        return credenciales;
    }

    public static int getContadorLineas() {
        return contadorLineas;
    }
}

