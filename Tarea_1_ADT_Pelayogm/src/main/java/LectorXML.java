import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LectorXML {

    public static void leerXML () {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                    try {
                        Document document = builder.parse(new File("paises.xml"));

                    }
            } catch (Exception e) {
                System.out.println("Error en la lectura");
            }
    }
}
