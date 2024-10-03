package tarea_804;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique ruta del archivo: ");
        String rutaArchivo = scanner.next();
        System.out.print("¿Cómo se llama el archivo? ");
        String nombreArchivo = scanner.next();
        nombreArchivo += ".txt";
        File file = new File(rutaArchivo, nombreArchivo);
        leerArchivo(file);
    }

    public static void leerArchivo (File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                int i;
                int contadorCaracteres = 0;
                int contadorPalabras = 0;
                int contadorLineas = 1;
                while ((i = bufferedReader.read()) != -1) {
                    contadorCaracteres++;
                    if (i == 32) {
                        contadorPalabras++;
                    }

                    if (i == 10) {
                        contadorLineas++;
                    }
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("En el documento hay, ");
                stringBuilder.append(contadorCaracteres);
                stringBuilder.append(" caracteres, ");

                stringBuilder.append("hay ");
                stringBuilder.append(contadorPalabras);
                stringBuilder.append(" palabras ");

                stringBuilder.append("y hay ");
                stringBuilder.append(contadorLineas);
                stringBuilder.append(" lineas. ");

                System.out.print(stringBuilder);

            } catch (Exception e) {
                System.out.println("Fin de Fichero");
            }
        } catch (Exception e) {
            System.out.println("No se ha podido leer el archivo");
        }
    }
}
