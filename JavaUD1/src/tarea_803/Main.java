package tarea_803;

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

        System.out.print("Indique ruta del archivo que va a recibir la copia: ");
        String rutaArchivoDuplicado = scanner.next();
        System.out.print("¿Cómo se llama el archivo que va a recibir la copia? ");
        String nombreArchivoDuplicado = scanner.next();
        nombreArchivoDuplicado += ".txt";
        File fileCopiado = new File(rutaArchivoDuplicado, nombreArchivoDuplicado);

        leerYescribirArchivo(file, fileCopiado);

    }

    public static void leerYescribirArchivo (File file, File fileDuplicado) {
        FileReader fileReader;
        FileWriter fileWriter;
            try {
                fileReader = new FileReader(file);
                fileWriter = new FileWriter(fileDuplicado);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    try {
                        int i;
                        int contadorCaracteres = 0;
                        while ((i = bufferedReader.read())!= -1) {
                            bufferedWriter.write((char)i);
                            contadorCaracteres++;
                        }
                        bufferedWriter.close();
                        bufferedReader.close();

                    fileWriter.close();
                    fileReader.close();

                        System.out.println("Hay " + contadorCaracteres + " en el documento" );
                        System.out.println("El fin");
                    } catch (Exception e) {
                        System.out.print("No se ha podido ejecutar la acción correctamente");
                    }

            } catch (Exception e) {
                System.out.print("Ha habido una complicación tecnica");
            }
    }
}
