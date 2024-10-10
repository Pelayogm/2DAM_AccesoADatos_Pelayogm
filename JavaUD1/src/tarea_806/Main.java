package tarea_806;

import java.io.File;
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

    }

    public static void rellenarNumeros (File file) {

    }
}
