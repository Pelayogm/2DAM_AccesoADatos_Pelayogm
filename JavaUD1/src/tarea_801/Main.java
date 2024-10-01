package tarea_801;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList <Integer> listEdades = new ArrayList<>();

    public static void main (String [] args) {
        File file = new File("src/tarea_801/entrada801.dat");
        leerArchivo(file);
        leerLista();
        File file2 = new File(".", "estadistica801.dat");
        calculoDeEstadisticas(file2);
    }

    public static void leerArchivo (File file) {
        FileInputStream fileInputStream;
        try {
                fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
                    while (true) {
                        //System.out.println(dataInputStream.readInt());
                        listEdades.add(dataInputStream.readInt());
                    }

            } catch (Exception e) {
                System.out.println("Fin del archivo");
            }
    }

    public static void leerLista () {
        for (int i = 0; i < listEdades.size(); i++) {
            System.out.println(listEdades.get(i));
        }
    }

    public static void calculoDeEstadisticas (File file) {
        int acumuladorEdades = listEdades.get(0);
            for (int i = 1; i < listEdades.size(); i++) {
                acumuladorEdades += listEdades.get(i);
            }
        int media = acumuladorEdades / listEdades.size();
            System.out.println("La Media es: " + media);
        ArrayList<Integer> listEdadesCuadrado = new ArrayList<>();
            for (int i = 0; i < listEdades.size(); i++) {
                int nume = listEdades.get(i);
                nume -= media;
                listEdadesCuadrado.add((nume * nume));
            }
        int acumuladorEdadesCuadrado = listEdades.get(0);
        for (int i = 1; i < listEdades.size(); i++) {
            acumuladorEdadesCuadrado += listEdadesCuadrado.get(i);
        }
        int mediaCuadrado = acumuladorEdadesCuadrado / listEdadesCuadrado.size();
        double raizCuadrada = Math.sqrt(mediaCuadrado);
        escribirFichero(file, raizCuadrada);
    }

    public static void escribirFichero (File file, double raizCuadrada) {
        FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                        DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
                            dataOutputStream.writeDouble(raizCuadrada);
                        dataOutputStream.close();
                    bufferedOutputStream.close();
                fileOutputStream.close();

            } catch (Exception e) {
                System.out.println("No se ha podido escribir");
            }
    }


}
