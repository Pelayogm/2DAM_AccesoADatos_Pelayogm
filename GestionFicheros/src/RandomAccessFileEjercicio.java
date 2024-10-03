import java.io.*;
import java.util.Scanner;

public class RandomAccessFileEjercicio {

    private static Scanner scanner = new Scanner(System.in);
    private static RandomAccessFile random = null;

    public static void main (String [] args) {
        File file = new File (".", "enteros.dat");
        System.out.println("¿En que posición?");
        int posicion = scanner.nextInt();
        mostrarFichero(file);
        escribirFichero(file, posicion);
        leerFichero(file, posicion);

    }

    public static void escribirFichero (File file, int i) {
        try {
            random = new RandomAccessFile(file, "rw");
            random.seek(2 *(i - 1));
            System.out.print("¿Qué numero me das?");
            int numero = scanner.nextInt();
            random.writeInt(numero);
            random.close();
        } catch (Exception e) {
            System.out.print("No se ha podido escribir el archivo correctamente");
        }
    }

    public static void leerFichero (File file, int i) {
        RandomAccessFile random;
        try {
            random = new RandomAccessFile(file, "r");
            random.seek(2 *(i - 1));
            System.out.println(random.read());
            random.close();

        } catch (Exception e) {
            System.out.println("No se ha podido encontrar el archivo");
        }
    }

    public static void mostrarFichero (File file) {
        FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                try {
                    int i;
                    while ((i = dataInputStream.read())!= -1) {
                        System.out.print((byte) i);
                    }
                } catch (Exception e) {
                    System.out.println("No se ha podido leer el archivo");
                }
            } catch (Exception e) {
                System.out.println("Ha habido un error");
            }
    }
}
