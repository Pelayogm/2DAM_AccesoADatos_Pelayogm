package tarea_802;

import java.io.*;
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
                       while ((i = bufferedReader.read()) != -1) {
                          if (i == 32) {
                              System.out.print("-");
                          } else {
                              char caracter = (char)i;
                              caracter = Character.toUpperCase(caracter);
                              System.out.print(caracter);
                          }
                       }
                   } catch (Exception e) {
                       System.out.println("Fin de Fichero");
                   }
            } catch (Exception e) {
                System.out.println("No se ha podido leer el archivo");
            }
    }
}
