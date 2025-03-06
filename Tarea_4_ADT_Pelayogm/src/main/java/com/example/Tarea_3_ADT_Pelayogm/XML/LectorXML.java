package com.example.Tarea_3_ADT_Pelayogm.XML;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;

public class LectorXML {
    public static ArrayList<String> listPaises = new ArrayList<>();

    public static void leerXML () {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser;

            try {
                parser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = parser.getXMLReader();

                GestorDeContenidos gestorDeContenidos = new GestorDeContenidos();
                xmlReader.setContentHandler(gestorDeContenidos);
                InputSource inputSource = new InputSource("src/main/java/com/example/Tarea_3_ADT_Pelayogm/ArchivosDelPrograma/paises.xml");
                try {
                    xmlReader.parse(inputSource);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.out.println("Error en la preparacion para la lectura");
            }
    }

    public static boolean comprobarNacionalidadConXML (String userNacionalidad) {
        for (int i = 0; i < listPaises.size(); i++) {
            if (listPaises.get(i).equals(userNacionalidad)) {
                return true;
            }
        }
        return false;
    }

}
