import java.io.*;
import java.util.Scanner;

public class ObjetosBinariosLecturaYEscritura {

    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File(".", "ObjetoPersona.dat");
            System.out.println("Introduzca nombre:");
                String nombre = scanner.nextLine();
            System.out.println("Introduzca edad:");
                int edad = scanner.nextInt();
                scanner.nextLine();
            System.out.println("Introduzca DNI:");
                String dni = scanner.nextLine();

            Persona persona = new Persona(nombre, edad, dni);

            try {
                escribirArchivo(file, persona);
                    try {
                        leerArchivo(file);
                    } catch (Exception e) {
                        System.out.println("Error en la lectura");
                    }
            } catch (Exception e) {
                System.out.println("Error en la escritura");
            }

    }

    public static void escribirArchivo (File file, Persona persona) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(persona);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Error en la escritura del archivo");
        }
    }

    public static void leerArchivo (File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                try {
                    while (true) {
                        System.out.println(objectInputStream.readObject());
                    }
                } catch (Exception e) {
                    System.out.println("Fin del archivo");
                }
        } catch (Exception e) {
            System.out.println("Error en la lectura del archivo");
        }
    }
}
