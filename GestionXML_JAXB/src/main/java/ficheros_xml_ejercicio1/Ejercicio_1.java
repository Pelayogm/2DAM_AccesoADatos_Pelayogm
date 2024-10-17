package ficheros_xml_ejercicio1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;


public class Ejercicio_1 {
    public static void main (String [] args) {
        crearFicheroYRellenar();
        crearFicheroXML();
    }

    public static void crearFicheroYRellenar () {
        ArrayList <Persona_EJ1> listaPersonaEJ1s = new ArrayList<>();

        Persona_EJ1 personaEJ1 = new Persona_EJ1("Leston", "118976432C", 654_33_22_90, 48);
        Persona_EJ1 personaEJ12 = new Persona_EJ1("Patiño", "118543432G", 694_33_22_90, 64);
        Persona_EJ1 personaEJ13 = new Persona_EJ1("Olga", "99977323F", 654_33_54_90, 53);
        Persona_EJ1 personaEJ14 = new Persona_EJ1("Nacho", "65646363G", 985_22_54_21, 41);

        listaPersonaEJ1s.add(personaEJ1);
        listaPersonaEJ1s.add(personaEJ12);
        listaPersonaEJ1s.add(personaEJ13);
        listaPersonaEJ1s.add(personaEJ14);

        File file = new File(".", "FichPersona.dat");
        FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
                   try {
                       for (int i = 0; i < listaPersonaEJ1s.size(); i++) {
                           objectOutputStream.writeObject(listaPersonaEJ1s.get(i));
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

    public static void getDatosFicheroDat (File file, ArrayList<Persona_EJ1> listPersonaEJ1s) {
        FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
                    try {
                        while (true) {
                            listPersonaEJ1s.add((Persona_EJ1)objectInputStream.readObject());
                        }
                    } catch (Exception e) {
                        System.out.println("Fin de la lectura del archivo");
                    }

            } catch (Exception e) {
                System.out.println("No se ha podido leer el archivo");
            }
    }

    public static void crearFicheroXML () {
        DocumentBuilderFactory factory;
        try {
            factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = builder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "Personas", null);
            generadorXML(document);
            //cargarXMLParaLeer(builder);

        } catch (Exception e) {
            System.out.println("No se ha podido crear el Document Builder");
        }
    }

    public static void generadorXML (Document document) {
        document.setXmlVersion("1.0");
        Element element = document.createElement("Persona");
        File file = new File(".", "FichPersona.dat");
        ArrayList <Persona_EJ1> listParaElementos = new ArrayList<>();

        getDatosFicheroDat(file, listParaElementos);
        document.getDocumentElement().appendChild(element);

            for (int i = 0; i < listParaElementos.size(); i++) {
                //System.out.println("Cogiendo Nombre");
                crearElementoXML("Nombre", listParaElementos.get(i).getNombre(), document, element);
                //System.out.println("Cogiendo DNI");
                crearElementoXML("DNI", listParaElementos.get(i).getDni(), document, element);
                //System.out.println("Cogiendo Telefono");
                String telefono = Integer.toString( listParaElementos.get(i).getTelefono());
                crearElementoXML("Telefono", telefono, document, element);
                //System.out.println("Cogiendo Edad");
                String edad = Integer.toString(listParaElementos.get(i).getEdad());
                crearElementoXML("Edad", edad, document, element);
            }

        Source source = new DOMSource(document);
        //Result result = new StreamResult(new java.io.File("PersonasDom.xml"));
        Result resultOut = new StreamResult(System.out);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            try {
                transformer.transform(source, resultOut);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearElementoXML (String tipoDatoEtiqueta, String datoDeEtiqueta, Document document, Element raiz) {
        Element element = document.createElement(tipoDatoEtiqueta);
        Text text = document.createTextNode(datoDeEtiqueta);
        raiz.appendChild(element);
        element.appendChild(text);
    }

    public static void cargarXMLParaLeer (DocumentBuilder documentBuilder) {
        try {
            Document document = documentBuilder.parse("PersonasDom.xml");
            System.out.println(document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("Persona");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (Node.ELEMENT_NODE == node.getNodeType()) {
                        Element element = (Element) node;
                        System.out.println(element.getElementsByTagName("Nombre").item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("DNI").item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("Telefono").item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("Edad").item(0).getTextContent());
                    }
                }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
