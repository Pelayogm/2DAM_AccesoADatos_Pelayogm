
import java.io.FileReader;

public class LeerArchivo {

    public static void main (String [] args) {

        try {
            int i;
            FileReader fileReader = new FileReader("Fichero.txt");
            while ((i = fileReader.read()) != 1) {
                System.out.print((char)i);

            }
            fileReader.close();

        } catch (Exception e) {
            System.out.print("No hay fichero");
        }
    }




}
