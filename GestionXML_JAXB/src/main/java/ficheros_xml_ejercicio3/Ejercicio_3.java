package ficheros_xml_ejercicio3;

import ficheros_xml_ejercicio1.Persona_EJ1;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;

public class Ejercicio_3 {
    public static void main (String [] args) {
        crearFicheroYRellenar();
        usarJAXB();
    }

    public static void crearFicheroYRellenar () {
        ArrayList <Persona> listaPersonas = new ArrayList<>();

        Persona persona = new Persona("Leston", "118976432C", 654_33_22_90, 48);
        Persona persona2 = new Persona("Patiño", "118543432G", 694_33_22_90, 64);
        Persona persona3 = new Persona("Olga", "99977323F", 654_33_54_90, 53);
        Persona persona4 = new Persona("Nacho", "65646363G", 985_22_54_21, 41);

        listaPersonas.add(persona);
        listaPersonas.add(persona2);
        listaPersonas.add(persona3);
        listaPersonas.add(persona4);

        File file = new File(".", "FichPersonaJAXB.dat");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            try {
                for (int i = 0; i < listaPersonas.size(); i++) {
                    objectOutputStream.writeObject(listaPersonas.get(i));
                }
                objectOutputStream.close();
            } catch (Exception e) {
                System.out.println("Final de la escritura");
            }
            bufferedOutputStream.close();
            fileOutputStream.close();
            System.out.println("Final del apartado");

        } catch (Exception e) {
            System.out.println("No he encontrado el fichero");
        }
    }

    public static void leerArchivoDat (File file, ArrayList<Persona> personasList) {
        FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
                    try {
                        while (true) {
                            personasList.add((Persona) objectInputStream.readObject());
                        }
                    } catch (Exception e) {
                        System.out.println("Fin del archivo");
                    }
            } catch (Exception e) {
                System.out.println("No se puede leer el archivo");
            }
    }


    public static void usarJAXB () {
        JAXBContext jaxbContext;
        File file = new File(".", "FichPersonaJAXB.dat");
        ArrayList <Persona> listPersonas = new ArrayList<>();
        leerArchivoDat(file, listPersonas);

        Personas personas = new Personas();
        personas.setListPersonas(listPersonas);

        try {
            jaxbContext = JAXBContext.newInstance(Personas.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(personas, System.out);
            marshaller.marshal(personas, new File(".", "PersonasJAXB.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
