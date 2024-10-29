import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class Exportar {

    public static void ExportarCarnet () {
        JAXBContext context;
        Carnet carnet = new Carnet();
            try {
                context = JAXBContext.newInstance(Carnet.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(carnet, System.out);
                marshaller.marshal(carnet, new File(".", "Carnet.xml"));

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
