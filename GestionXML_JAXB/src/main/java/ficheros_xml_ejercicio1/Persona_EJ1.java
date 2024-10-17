package ficheros_xml_ejercicio1;


import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"Nombre", "DNI", "Telefono", "Edad"})
public class Persona_EJ1 implements Serializable{
    private String nombre;
    private String dni;
    private int telefono;
    private int edad;

    public Persona_EJ1(String nombre, String dni, int telefono, int edad) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.edad = edad;
    }

    public Persona_EJ1() {

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
