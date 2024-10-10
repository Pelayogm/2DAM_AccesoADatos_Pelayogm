
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Main {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation domImplementation = builder.getDOMImplementation();
                Document document = domImplementation.createDocument(null, "Empleados",  null);
                document.setXmlVersion("1.0");
                Element raiz = document.createElement("empleado");
                document.getDocumentElement().appendChild(raiz);
            } catch (Exception e) {
                System.out.println("No se ha podido");
            }
        }

    public static void crearXML () {

    }

    public static void cargarXML () {

    }

    }


