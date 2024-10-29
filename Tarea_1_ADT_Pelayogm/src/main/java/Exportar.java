import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Exportar {

    public static void ExportarCarnet () {
        DocumentBuilderFactory factory;
            try {
                factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation domImplementation = builder.getDOMImplementation();
                Document document = domImplementation.createDocument(null, "carnet", null);
                    try {
                        document.setXmlVersion("1.0");
                        File file = new File("");
                    } catch (Exception e) {
                        System.out.println("Error con el Document");
                    }
                
            } catch (Exception e) {
                System.out.println("No se ha creado el Document Builder Factory");
            }
    }
}
