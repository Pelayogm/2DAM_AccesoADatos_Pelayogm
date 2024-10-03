package tarea_805;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Integer> listaNumeros;
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
                listaNumeros = new ArrayList<>();
                int i;
                int contadorNumeros = 0;
                while ((i = bufferedReader.read())!= -1) {

                    if (i == 32) {

                    }
                    int numero = 0;
                        listaNumeros.add(numero);
                        contadorNumeros++;
                    }
                bufferedReader.close();
                fileReader.close();

                int media = calcularMedia();
                System.out.println("Media de los numeros: " + media);
                System.out.println("Hay " + contadorNumeros + " numeros");
            } catch (Exception e) {
                System.out.println("Fin de Fichero");
            }

        } catch (Exception e) {
            System.out.println("No se ha podido leer el archivo");
        }
    }

    public static int calcularMedia () {
        int acumulador = 0;
        for (int i = 0; i < listaNumeros.size(); i++) {
            acumulador = acumulador + listaNumeros.get(i);
        }

        acumulador = acumulador / listaNumeros.size();
        return acumulador;
    }
}
