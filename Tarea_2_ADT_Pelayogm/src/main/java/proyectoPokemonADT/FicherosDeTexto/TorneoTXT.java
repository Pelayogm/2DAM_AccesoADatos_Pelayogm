package proyectoPokemonADT.FicherosDeTexto;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import proyectoPokemonADT.DTO.GimnasioDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Entidades.GimnasioEntidad;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TorneoTXT {

    public static void exportarTorneo (TorneoDTO torneo) throws IOException {
        String nombreTorneo = torneo.getNombre();
        nombreTorneo += ".txt";
        File file = new File("src/main/java/proyectoPokemonADT/ArchivosTorneos/", nombreTorneo);
        if (!file.exists()) {
            file.createNewFile();
            TorneoTXT.escribirFichero(torneo, file);
            System.out.println("Torneo exportado a fichero");
        }
        TorneoTXT.escribirFichero(torneo, file);
        System.out.println("Torneo exportado a fichero");
    }

    public static void escribirFichero (TorneoDTO torneo, File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            try {
                fileWriter.write("Nombre Torneo: " + torneo.getNombre() + "\n");
                fileWriter.write("Region Torneo: " + torneo.getCodRegion() + "\n");
                fileWriter.write("Combates del torneo" + "\n");
                for (int i = 0; i < torneo.getCombatesDelTorneo().size(); i++) {
                    fileWriter.write("Combate " + i + ": " + torneo.getCombatesDelTorneo().get(i).getFecha() + " | " + torneo.getCombatesDelTorneo().get(i).getId() + "\n");
                }
                fileWriter.write("Puntos del torneo: " + torneo.getPuntosVictoria());
                fileWriter.close();
            } catch (Exception e) {
                System.out.println("Error en la escritura");
            }
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public static void exportarGimnasio (GimnasioDTO gimnasioDTO) throws IOException {
        String nombreTorneo = gimnasioDTO.getNombreGimnasio();
        nombreTorneo += ".xml";
            TorneoTXT.escribirXMLGimnasio(gimnasioDTO, nombreTorneo);
            System.out.println("Torneo exportado a fichero");
        }


    public static void escribirXMLGimnasio (GimnasioDTO gimnasioDTO, String nombreTorneo) {
        DocumentBuilderFactory factory;
        try {
            factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = builder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "gimnasio_Torneo", null);
            try {
                document.setXmlVersion("1.0");
                document.getDocumentElement();
                CrearElementoXML("idGimnasio", String.valueOf(gimnasioDTO.getIdGimnasio()), document, document.getDocumentElement());
                CrearElementoXML("nombreGimnasio", gimnasioDTO.getNombreGimnasio(), document, document.getDocumentElement());
                CrearElementoXML("tipoGimnasio", gimnasioDTO.getTipoGimnasio(), document, document.getDocumentElement());
                CrearElementoXML("nivelGimnasio", String.valueOf(gimnasioDTO.getNivelGimnasio()), document, document.getDocumentElement());

                Source source = new DOMSource(document);
                Result result = new StreamResult(new java.io.File("src/main/java/proyectoPokemonADT/ArchivosTorneos/", nombreTorneo));
                try {
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    try {
                        transformer.transform(source, result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    System.out.println("Error intentando crear el transformer");
                }
            } catch (Exception e) {
                System.out.println("Error con el document");
            }
        } catch (Exception e) {
            System.out.println("No se ha creado el Document Builder Factory");
        }
    }

    public static void CrearElementoXML (String tipoDatoEtiqueta, String datoEtiqueta, Document document, Element raiz) {
        Element element = document.createElement(tipoDatoEtiqueta);
        Text text = document.createTextNode(datoEtiqueta);
        raiz.appendChild(element);
        element.appendChild(text);
    }
}
