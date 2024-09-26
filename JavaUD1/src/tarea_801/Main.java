package tarea_801;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {
    private static ArrayList <Integer> listEdades;

    public static void main (String [] args) {
        File file = new File(".", "estadistica801.dat");

    }

    public static void leerArchivo () {
        listEdades = new ArrayList<>();
        File file = new File(".", "entrada801");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    while (true) {
                        System.out.print((char)dataInputStream.readByte());
                        listEdades.add((int) dataInputStream.readByte());
                    }

            } catch (Exception e) {
                System.out.println("No se ha podido leer el archivo");
            }
    }

    public void calculoDeEstadisticas (File file) {
        int acumuladorEdades = listEdades.get(0);
            for (int i = 1; i < listEdades.size(); i++) {
                acumuladorEdades += listEdades.get(i);
            }
        int media = acumuladorEdades / listEdades.size();
        ArrayList<Integer> listEdadesCuadrado = new ArrayList<>();
            for (int i = 0; i < listEdades.size(); i++) {

            }
    }


}
