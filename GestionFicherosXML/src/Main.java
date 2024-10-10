
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = builder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "Empleados",  null);
            cargarXML(builder);
            //crearXML(document);
    }

    public static void crearXML (Document document) {
        document.setXmlVersion("1.0");
        Element element = document.createElement("empleado");
        document.getDocumentElement().appendChild(element);
        System.out.println("¿Apellido del empleado?");
        String apellido = scanner.nextLine();
        crearElemento("Apellido", apellido, document, element);
        System.out.println("¿Numero del empleado?");
        int numero = scanner.nextInt();
        crearElemento("Numero", Integer.toString(numero), document, element);
        System.out.println("¿Salario del empleado?");
        float salario = scanner.nextFloat();
        crearElemento("Salario", Float.toString(salario), document, element);

        Source source = new DOMSource(document);
        Result result = new StreamResult(new java.io.File("empleados.xml"));
        Result resultOut = new StreamResult(System.out);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                try {
                    transformer.transform(source, resultOut);
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void crearElemento (String datoEmpleado, String valorEmpleado, Document document, Element raiz) {
        Element element = document.createElement(datoEmpleado);
        Text text = document.createTextNode(valorEmpleado);
        raiz.appendChild(element);
        element.appendChild(text);
    }

    public static void cargarXML (DocumentBuilder documentBuilder) {
        try {
            Document document = documentBuilder.parse(new File("empleados.xml"));
            System.out.println(document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("empleado");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                    if (Node.ELEMENT_NODE == node.getNodeType()) {
                        Element element = (Element) node;
                        System.out.println(element.getElementsByTagName("Numero").item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("Apellido").item(0).getTextContent());
                        System.out.println(element.getElementsByTagName("Salario").item(0).getTextContent());
                    }
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    }


