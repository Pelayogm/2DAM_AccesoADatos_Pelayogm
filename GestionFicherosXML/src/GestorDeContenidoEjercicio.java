import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorDeContenidoEjercicio extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Principio del documento");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Final de documento");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Comienzo el elemento: " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Fin del elemento: " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String caracteres = new String(ch,start,length);
        System.out.println("Caracteres: " + caracteres);
    }
}
