import java.io.*;
import java.util.ArrayList;

public class GestorArchivosDat {
    public static ArrayList <Entrenador> listEntrenadores = Sesion.getListEntrenadores();

    public static void escribirEntrenadoresDat (File file, Entrenador entrenador) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, true);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(entrenador);
            objectOutputStream.close();
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Error en la escritura");
        }
    }

    public static void leerEntrenadoresDat (File file) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            try {
                while (true) {
                    listEntrenadores.add((Entrenador) objectInputStream.readObject());
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean comprobarEntrenadorDat (File file, String nombre) {
        leerEntrenadoresDat(file);
        for (int i = 0; i < listEntrenadores.size(); i++) {
            if (listEntrenadores.get(i).getNombre().equals(nombre)) {
                if (listEntrenadores.get(i).isEsEntrenador()) {
                    return true;
                }
            }
        }
        return false;
    }
}
