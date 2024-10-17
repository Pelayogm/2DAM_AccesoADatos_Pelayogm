package ficheros_xml_ejercicio3;

import ficheros_xml_ejercicio1.Persona_EJ1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Personas {
    ArrayList<Persona> listPersonas;

    public Personas(ArrayList<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    public Personas () {

    }

    @XmlElementWrapper(name = "ListaPersonas")
    @XmlElement(name = "Persona")
    public ArrayList<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(ArrayList<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }
}
