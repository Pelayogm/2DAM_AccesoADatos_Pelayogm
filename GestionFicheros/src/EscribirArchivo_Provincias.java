import java.io.File;
import java.io.FileWriter;

public class EscribirArchivo_Provincias {

    public static void main (String [] args) {
        try {
            File f1 = new File(".", "Fichero3.txt");
            FileWriter fileWriter = new FileWriter(f1);
            String [] escribir = new String[] {"Asturias", ", ", "Toledo", ", ", "Valencia", ", ", "Galicia"};

            for (int i = 0; i < escribir.length; i++) {
                fileWriter.write(escribir[i] + "\n");

            }
            //fileWriter.write(escribir);
            fileWriter.close();

            System.out.println("Los textos se han transcrito");

        } catch (Exception e) {
            System.out.println("No se puede proceder a la escritura");
        }
    }
}
