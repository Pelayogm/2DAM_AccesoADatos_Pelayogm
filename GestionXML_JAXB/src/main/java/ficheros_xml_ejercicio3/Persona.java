package ficheros_xml_ejercicio3;


import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


@XmlType(propOrder = {"Nombre", "DNI", "Telefono", "Edad"})
public class Persona implements Serializable {
    private String nombre;
    private String dni;
    private int telefono;
    private int edad;

    public Persona(String nombre, String dni, int telefono, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.edad = edad;
    }

    public Persona() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }
}
