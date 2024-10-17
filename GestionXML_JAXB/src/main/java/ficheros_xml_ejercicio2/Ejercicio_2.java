package ficheros_xml_ejercicio2;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Ejercicio_2 {
    public static void main (String [] args) {
        usarSAX();
    }

    public static void usarSAX () {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser;
            try {
                parser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = parser.getXMLReader();

                GestorDeContenidosXML gestorDeContenidosXML = new GestorDeContenidosXML();
                xmlReader.setContentHandler(gestorDeContenidosXML);

                InputSource inputSource = new InputSource("PersonasDom.xml");
                    try {
                        xmlReader.parse(inputSource);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            } catch (Exception e) {
                System.out.println("Error con el SAX");
            }
    }
}
