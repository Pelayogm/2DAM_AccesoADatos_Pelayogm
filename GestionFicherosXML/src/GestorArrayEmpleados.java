import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestorArrayEmpleados extends DefaultHandler {


    static Empleado empleado1 = new Empleado("Vazquez", 2, 1800.50);
    static Empleado empleado2 = new Empleado("Lorenzo", 3, 1920.50);
    static Empleado empleado3 = new Empleado("Leston", 4, 2789.50);

    boolean apellido = false;
    boolean numero = false;
    boolean salario = false;

    static Empleado [] empleados = new Empleado[] {empleado1, empleado2, empleado3};

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
            if (qName.equals("empleado")) {

            }
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
