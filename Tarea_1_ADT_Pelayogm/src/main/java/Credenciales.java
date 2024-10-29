import java.io.*;
import java.util.ArrayList;

public class Credenciales {
    private static ArrayList <String> credenciales = Sesion.getCredenciales();

    public static void leerFichero(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String linea = bufferedReader.readLine();
                while (linea != null) {
                    credenciales.add(linea);
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

    public static void escribirFichero (File file, String datosParaEscribir) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            try {
                fileWriter.write(datosParaEscribir + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se ha podido escribir");
            }
        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo para escribir");
        }
    }
}
