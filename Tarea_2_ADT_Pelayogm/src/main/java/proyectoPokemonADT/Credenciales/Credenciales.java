package proyectoPokemonADT.Credenciales;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Credenciales {
    private static ArrayList<String> credenciales = new ArrayList<>();

    public static void leerFichero(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String linea = bufferedReader.readLine();
                while (linea != null) {
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

    public static void escribirFichero (File file, String usuario, String constrasena, String rolUsuario, String idUsuario) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            try {
                fileWriter.write(usuario + " ");
                fileWriter.write(constrasena + " ");
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

    public static boolean comprobarCredenciales (String usuario, String contrasena) {
        for (int i = 0; i < credenciales.size(); i+=4) {
            if (credenciales.get(i).equals(usuario) && credenciales.get(i + 1).equals(contrasena)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getCredenciales() {
        return credenciales;
    }
}

