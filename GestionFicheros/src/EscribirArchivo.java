import java.io.File;
import java.io.FileWriter;

public class EscribirArchivo {

    public static void main (String [] args) {
        try {
            File f1 = new File(".", "Fichero2.txt");
            FileWriter fileWriter = new FileWriter(f1);
            String escribir = "Monolitico";

            // char [] cadenaTexto = escribir.toCharArray();
            //           for (int i = 0; i < escribir.length(); i++) {
            //
            //             fileWriter.write(cadenaTexto[i]);
            //
            //            }
            fileWriter.write(escribir);
            fileWriter.close();

            System.out.println("La palabra " + escribir + " ha sido transcrita");

        } catch (Exception e) {
            System.out.println("No se puede proceder a la escritura");
        }
    }
}
