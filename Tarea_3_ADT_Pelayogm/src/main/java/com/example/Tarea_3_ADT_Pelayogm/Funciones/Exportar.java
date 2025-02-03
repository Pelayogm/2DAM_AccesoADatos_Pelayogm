package com.example.Tarea_3_ADT_Pelayogm.Funciones;

import com.example.Tarea_3_ADT_Pelayogm.Administradores.AdminTorneos;
import com.example.Tarea_3_ADT_Pelayogm.Entidades.*;
import com.example.Tarea_3_ADT_Pelayogm.Menus.Menus;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoAdminServiciosImplementacion;
import com.example.Tarea_3_ADT_Pelayogm.Servicios.TorneoServiciosImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Exportar {

    @Autowired
    Menus menu;
    @Autowired
    TorneoAdminServiciosImplementacion torneoAdminServiciosImplementacion;
    @Autowired
    TorneoServiciosImplementacion torneoServiciosImplementacion;
    @Autowired
    GestionTorneos gestionTorneos;

    public void ExportarTorneo (Usuario usuario) {
        if (usuario instanceof AdminTorneos) {
            Scanner entrada = new Scanner(System.in);
            AdminTorneos adminTorneos = (AdminTorneos) usuario;
            int idAdminTorneos = (int) ((AdminTorneos) usuario).getIdUsuario();
            List<Torneo> listaTorneos =torneoServiciosImplementacion.torneosDelAdministrador(idAdminTorneos);
            int contador = 0;

            if (!listaTorneos.isEmpty()) {
                System.out.println("Torneos de " + ((AdminTorneos) usuario).getNombreAdminTorneo());
                for (int i = 0; i < listaTorneos.size(); i++) {
                        System.out.println(contador + " - " + listaTorneos.get(i).getNombreTorneo());
                        contador++;
                    }

                System.out.println("Selecciona el torneo que quieras exportar.");
                int opcionUsuario = entrada.nextInt();
                try {
                    gestionTorneos.crearFichero(listaTorneos.get(opcionUsuario));
                } catch (Exception e) {
                    System.out.println("No se ha podido exportar el torneo. Intentalo de nuevo más tarde");
                }
            } else {
                System.out.println("No tienes ningún torneo asignado");
            }
        } else {
            System.out.println("No dispones de los permisos necesarios para acceder a este menú.");
            menu.menuInicial();
        }
    }

    public void ExportarCarnet (Entrenador entrenador) {
        DocumentBuilderFactory factory;
        try {
            factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = builder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "carnet_entrenador", null);
            try {
                document.setXmlVersion("1.0");
                document.getDocumentElement();

                CrearElementoXML("id", entrenador.getIdEntrenador().toString(), document, document.getDocumentElement());
                    LocalDate fechaCarnet = entrenador.getCarnetEntrenador().getFechaExpedicion();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
                    String fechaCarnetString = fechaCarnet.format(dateTimeFormatter);
                CrearElementoXML("fecha_exp", fechaCarnetString, document, document.getDocumentElement());

                Element elementoEntrenador = document.createElement("entrenador");
                    CrearElementoXML("nombre_entrenador", entrenador.getNombreEntrenador(), document, document.getDocumentElement());
                    CrearElementoXML("nacionalidad_entrenador", entrenador.getNacionalidadEntrenador(), document, elementoEntrenador);
                        LocalDate fechaActual = LocalDate.now();
                        String fechaActualString = fechaActual.format(dateTimeFormatter);
                    CrearElementoXML("hoy", fechaActualString, document, document.getDocumentElement());
                document.getDocumentElement().appendChild(elementoEntrenador);
                //TORNEOS ENTRENADOR
                Element elementTorneos = document.createElement("Torneos");

                document.getDocumentElement().appendChild(elementTorneos);
                for (int i = 0; i < entrenador.getListaTorneos().size(); i++) {
                    //ETIQUETA TORNEO POR CADA TORNEO DE LA LISTA QUE TIENE EL OBJETO ENTRENADOR
                    Element elementTorneo = document.createElement("torneo");
                    CrearElementoXML("nombre", entrenador.getListaTorneos().get(i).getNombreTorneo(), document, elementTorneo);
                    CrearElementoXML("region", entrenador.getListaTorneos().get(i).getCodigoTorneo(), document, elementTorneo);
                    if (entrenador.getListaTorneos().get(i).getIdGanador() == 0 || entrenador.getListaTorneos().get(i).getIdGanador() == -1) {
                        CrearElementoXML("victoria", "No Finalizado", document, elementTorneo);
                    } else if (entrenador.getListaTorneos().get(i).getIdGanador() == entrenador.getIdEntrenador()) {
                        CrearElementoXML("victoria", "true", document, elementTorneo);
                    }

                    Element elementCombates = document.createElement("Combates");
                    //Cogemos el torneo actual y recorremos su lista de combates
                    Torneo torneo = entrenador.getListaTorneos().get(i);
                    for (int x = 0; x < torneo.getCombates().size(); x++) {
                        Combate combateActual = torneo.getCombates().get(x);

                        Element elementCombate = document.createElement("combate");
                        String idCombate = String.valueOf(combateActual.getIdCombate());
                        String fechaCombate = String.valueOf(combateActual.getFechaCombate());

                        CrearElementoXML("id", idCombate, document, elementCombate);
                        CrearElementoXML("fecha", fechaCombate, document, elementCombate);
                        if (combateActual.getCombateEntrenador().getIdGanador() == entrenador.getIdEntrenador()) {
                            CrearElementoXML("victoria","true", document, elementCombate);
                        } else {
                            if (combateActual.getCombateEntrenador().getIdGanador() == 0 ) {
                                CrearElementoXML("victoria","No participado", document, elementCombate);
                            } else {
                                CrearElementoXML("victoria","false", document, elementCombate);
                            }
                        }

                        elementCombates.appendChild(elementCombate);
                    }
                    elementTorneo.appendChild(elementCombates);

                    //APPEND A CADA ELEMENTO AL PADRE, LA ETIQUETA TORNEOS
                    elementTorneos.appendChild(elementTorneo);
                }
                //Fin de los torneos del Entrenador

                //Exportación del archivo
                Source source = new DOMSource(document);
                    String nombreFichero = entrenador.getNombreEntrenador();
                Result result = new StreamResult(new java.io.File("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosSalida/",nombreFichero + ".xml"));
                    try {
                        Transformer transformer = TransformerFactory.newInstance().newTransformer();
                        try {
                            transformer.transform(source, result);
                            System.out.println("Fichero XML exportado con éxito!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        System.out.println("No se ha podido exportar el fichero, error con el transformer.");
                    }
                //Fin de la exportación del fichero.

            } catch (Exception e) {
                System.out.println("No se ha creado el documento");
            }
        } catch (Exception e) {
            System.out.println("No se ha creado del Document Builder Factory");
        }
    }

    public void CrearElementoXML (String tipoDatoEtiqueta, String datoEtiqueta, Document document, Element raiz) {
        Element element = document.createElement(tipoDatoEtiqueta);
        Text text = document.createTextNode(datoEtiqueta);
        raiz.appendChild(element);
        element.appendChild(text);
    }
}
