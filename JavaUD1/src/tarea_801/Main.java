package tarea_801;

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static ArrayList <Integer> listEdades = new ArrayList<>();
    private static int media = 0;

    public static void main (String [] args) {
        File file = new File("src/tarea_801/entrada801.dat");
        leerArchivo(file);
        leerLista();
        File file2 = new File(".", "estadistica801.dat");
        calculoDeEstadisticas(file2);
        estadisticasAMostrar();
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
        //Se hace el acumulador de los numeros para calcular la media
        int acumuladorEdades = listEdades.get(0);
            for (int i = 1; i < listEdades.size(); i++) {
                acumuladorEdades += listEdades.get(i);
            }

        //Se hace la media
        media = acumuladorEdades / listEdades.size();
            //System.out.println("La Media es: " + media);

        //ArrayList para guardar las edades al Cuadrado
        ArrayList<Integer> listEdadesCuadrado = new ArrayList<>();
            for (int i = 0; i < listEdades.size(); i++) {
                int nume = listEdades.get(i);
                nume -= media;
                listEdadesCuadrado.add((nume * nume));
            }

        //Se hace el acumulador de todos los numeros elevados al cuadrado para hacer la medio
        int acumuladorEdadesCuadrado = listEdades.get(0);
        for (int i = 1; i < listEdades.size(); i++) {
            acumuladorEdadesCuadrado += listEdadesCuadrado.get(i);
        }

        //Se calcula la media aritmetica
        int mediaCuadrado = acumuladorEdadesCuadrado / listEdadesCuadrado.size();
        double raizCuadrada = Math.sqrt(mediaCuadrado);
        System.out.println("La desviacion estandar es: " + raizCuadrada);
        escribirFichero(file, raizCuadrada);
    }

    public static void estadisticasAMostrar () {
        System.out.println("Alumnos procesados: " + listEdades.size());
        System.out.println("La media es " + media);
        int contadorDeMenoresDeEdad = 0;
        int elMenor = listEdades.get(0);
        int elMayor = listEdades.get(0);

        if (elMayor < 18) {
            contadorDeMenoresDeEdad++;
        } else if (elMenor < 18) {
            contadorDeMenoresDeEdad++;
        } else {
            contadorDeMenoresDeEdad = 0;
        }
        for (int i = 1; i < listEdades.size(); i++) {
            if (elMayor < listEdades.get(i)) {
                elMayor = listEdades.get(i);
            }

            if (listEdades.get(i) < 18) {
                contadorDeMenoresDeEdad++;
            }

            if (elMenor > listEdades.get(i)) {
                elMenor = listEdades.get(i);
            }
        }

        System.out.println("El más joven tiene " + elMenor);
        System.out.println("El más viejo tiene " + elMayor);
        System.out.println("Hay " + contadorDeMenoresDeEdad + " menores de edad");

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
