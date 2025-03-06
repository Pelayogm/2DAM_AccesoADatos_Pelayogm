package com.example.Tarea_3_ADT_Pelayogm.XML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorDeContenidos extends DefaultHandler {
    private boolean esNombre = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("nombre")) {
            esNombre = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (esNombre) {
            String nombre = new String(ch, start, length).trim();
            LectorXML.listPaises.add(nombre);
            System.out.println(nombre);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("nombre")) {
            esNombre = false;
        }
    }
}
