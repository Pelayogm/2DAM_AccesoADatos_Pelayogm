import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class Ejemplo1_Javax {
    public static void main (String [] args) {
        Libreria libreria = new Libreria();
        rellenarLibreria(libreria);
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Libreria.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(libreria, System.out);
            marshaller.marshal(libreria, new File(".","libreria.xml"));
            unMarshaller();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rellenarLibreria(Libreria libreria) {
        Libro libro = new Libro("Charangas de la mar", "Paco Paez", "Sabemos Mas", "111A");
        Libro libro1 = new Libro("Cascarillas estadenses", "Rosario Veraz Cruz", "Estudiando", "211B");
        Libro libro2 = new Libro("Almendritas Rocosas", "Gustavo Ferraz", "Sabemos Mas", "112C");
        Libro libro3 = new Libro("Tragedias en las Meanas", "Luis M.L.L", "Leston Productions", "411D");

        ArrayList <Libro> listTemporal = new ArrayList<>();

        listTemporal.add(libro);
        listTemporal.add(libro1);
        listTemporal.add(libro2);
        listTemporal.add(libro3);

        libreria.setListaLibro(listTemporal);
    }

    public static void unMarshaller () {
        JAXBContext context;
            try {
                context = JAXBContext.newInstance(Libreria.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Libreria lib = (Libreria) unmarshaller.unmarshal(new File(".", "libreria.xml"));
                System.out.println("Final");
            }catch (Exception e) {
                e.printStackTrace();
            }
    }
}
