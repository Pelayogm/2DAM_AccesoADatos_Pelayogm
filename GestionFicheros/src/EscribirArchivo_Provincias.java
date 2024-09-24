import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class EscribirArchivo_Provincias {

    public static void main (String [] args) {
        try {
            File f1 = new File(".", "Fichero3.txt");
            FileWriter fileWriter = new FileWriter(f1);
            String [] escribir = new String[] {"Asturias", ", ", "Toledo", ", ", "Valencia", ", ", "Galicia"};

            for (int i = 0; i < escribir.length; i++) {
                fileWriter.write(escribir[i]);

            }
            //fileWriter.write(escribir);
            fileWriter.close();

            System.out.println("Las palabras " + Arrays.toString(escribir) + " han sido transcritas");

        } catch (Exception e) {
            System.out.println("No se puede proceder a la escritura");
        }
    }
}
