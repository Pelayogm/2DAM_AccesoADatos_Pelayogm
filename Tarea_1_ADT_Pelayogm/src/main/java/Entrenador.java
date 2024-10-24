import java.io.Serializable;
import java.util.Scanner;

public class Entrenador extends Usuario implements Serializable {
    private final long id;
    private final String nombre;
    private String nacionalidad;
    private boolean sesionIniciada = false;

    private final boolean esEntrenador = true;

    public boolean isEsEntrenador() {
        return esEntrenador;
    }

    public Entrenador(long id, String nombre, String nacionalidad) {
        setEstadoSesion(true);
        setUsuario(false);
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }


    public static Entrenador crearEntrenador (String nombreUsuario) {
        Scanner scanner_entrenador = new Scanner(System.in);
        System.out.println("¿Nombre?");
        System.out.println("Nombre elegido: " + nombreUsuario);
        System.out.println("Nacionalidades Admitidas");
        LectorXML.leerXML();
        System.out.println("¿Nacionalidad?");
        String nacionalidadEntrenador = scanner_entrenador.nextLine();

        if (LectorXML.comprobarNacionalidadConXML(nacionalidadEntrenador)) {
            Entrenador entrenador = new Entrenador(2, nombreUsuario, nacionalidadEntrenador);
            System.out.println("Bienvenido: " + nombreUsuario);
            return entrenador;

        } else {
            System.out.println("Datos mal introducidos");
        }
        return null;
    }
}
