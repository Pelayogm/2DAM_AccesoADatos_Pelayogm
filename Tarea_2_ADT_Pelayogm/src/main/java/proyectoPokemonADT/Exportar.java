package proyectoPokemonADT;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Exportar {

    public static void ExportarCarnet (Entrenador entrenador) {
        DocumentBuilderFactory factory;
            try {
                factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation domImplementation = builder.getDOMImplementation();
                Document document = domImplementation.createDocument(null, "carnet_Entrenador", null);
                    try {
                        document.setXmlVersion("1.0");
                        document.getDocumentElement();

                            CrearElementoXML("id", Long.toString(entrenador.getCarnet().getIdEntrenador()) ,document, document.getDocumentElement());
                                LocalDate dateCarnet = entrenador.getCarnet().getFechaExpedicion();
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                                String dateString = dateCarnet.format(dateTimeFormatter);
                            CrearElementoXML("fechaexp", dateString, document, document.getDocumentElement());

                        Element elementEntrenador = document.createElement("entrenador");
                            CrearElementoXML("nombre", entrenador.getNombre(), document, elementEntrenador);
                            CrearElementoXML("nacionalidad", entrenador.getNacionalidad(), document, elementEntrenador);
                                LocalDate fechaActual = LocalDate.now();
                                String dateActual = fechaActual.format(dateTimeFormatter);
                            CrearElementoXML("hoy", dateActual, document, document.getDocumentElement());
                        document.getDocumentElement().appendChild(elementEntrenador);

                        //ETIQUETA TORNEOS DEL ENTRENADOR
                        Element elementTorneos = document.createElement("Torneos");

                        document.getDocumentElement().appendChild(elementTorneos);
                            for (int i = 0; i < entrenador.getTorneosDelEntrenador().size(); i++) {
                                //ETIQUETA TORNEO POR CADA TORNEO DE LA LISTA QUE TIENE EL OBJETO ENTRENADOR
                                Element elementTorneo = document.createElement("torneo");
                                CrearElementoXML("nombre", entrenador.getTorneosDelEntrenador().get(i).getNombre(), document, elementTorneo);
                                CrearElementoXML("region", Character.toString(entrenador.getTorneosDelEntrenador().get(i).getCodRegion()), document, elementTorneo);

                                //APPEND A CADA ELEMENTO AL PADRE, LA ETIQUETA TORNEOS
                                elementTorneos.appendChild(elementTorneo);
                            }

                        Source source = new DOMSource(document);
                            String nombreFichero = entrenador.getNombre();
                        Result result = new StreamResult(new java.io.File(nombreFichero + ".xml"));
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
                        System.out.println("Error con el Document");
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
