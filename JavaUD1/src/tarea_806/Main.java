package tarea_806;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Double> listNumeros;
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique ruta del archivo: ");
        String rutaArchivo = scanner.next();
        System.out.print("¿Cómo se llama el archivo? ");
        String nombreArchivo = scanner.next();
        nombreArchivo += ".dat";
        try {
            File file = new File(rutaArchivo, nombreArchivo);
            rellenarNumeros(file);
            leerDecimales(file);
        } catch (Exception e) {
            File fileLista = new File(".");
            fileLista.list();
                for (int i = 0; i < fileLista.length(); i++) {
                    if (fileLista.isDirectory() && fileLista.getName().equals("Directoriong")) {
                        File file = new File("./Directoriong", "Dataceos.dat");
                        rellenarNumeros(file);
                        leerDecimales(file);
                    } else {
                        File carpeta = new File(".", "Directoriong");
                        carpeta.mkdir();
                        File file = new File("./Directoriong", "Dataceos.dat");
                    }
                }

        }
    }

    public static void rellenarNumeros (File file) {
        FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
                    double [] numeros = new double[] {30.5, 2.75, 1.3, 50, 4, 5, 9};
                        for (int i = 0; i < numeros.length; i++) {
                            dataOutputStream.writeDouble(numeros[i]);
                        }
                    dataOutputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();

            } catch (Exception e) {
                System.out.println("No se ha encontrado la fuente de datos");
            }
    }

    public static void leerDecimales (File file) {
        FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
                listNumeros = new ArrayList<>();
                    try {
                        while (true) {
                            listNumeros.add(dataInputStream.readDouble());
                        }
                    } catch (Exception e) {
                        System.out.println("Fin de la lectura del archivo");
                    }
                    dataInputStream.close();
                    bufferedInputStream.close();
                    fileInputStream.close();
                    compararNumeros();

            } catch (Exception e) {
                System.out.println("No he encontrado el fichero para leer");
            }
    }

    public static void compararNumeros () {
        double elMayor;
        double elMenor;
            try {
                elMenor = listNumeros.get(0);
                elMayor = listNumeros.get(0);

                for (int i = 1; i < listNumeros.size(); i++) {
                    if (listNumeros.get(i) < elMenor) {
                        elMenor = listNumeros.get(i);
                    }

                    if (listNumeros.get(i) > elMayor) {
                        elMayor = listNumeros.get(i);
                    }
                }
                System.out.println("El menor es: " + elMenor);
                System.out.println("El mayor es: " + elMayor);
            } catch (Exception e) {
                System.out.println("No se han podido comparar");
            }
    }
}
