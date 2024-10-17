package ficheros_xml_ejercicio2;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorDeContenidosXML extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Inicio del Fichero");
        System.out.println("---------------------");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("---------------------");
        System.out.println("Final del Fichero");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("______________________________");
        System.out.println("Inicio del elemento:" + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Final de elemento: " + qName);
        System.out.println("______________________________");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String caracteres = new String(ch,start,length);
        System.out.println("Contenido: " + caracteres);
    }
}
