import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class EjercicioConSax {

    public static void main (String [] args) {
        leerDocumento();
    }

    public static void leerDocumento() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            GestorDeContenidoEjercicio gestorDeContenido = new GestorDeContenidoEjercicio();
            xmlReader.setContentHandler(gestorDeContenido);

            InputSource inputSource = new InputSource("empleados.xml");

            try {
                xmlReader.parse(inputSource);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
