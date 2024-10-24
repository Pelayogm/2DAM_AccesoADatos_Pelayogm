import java.io.File;
import java.util.Scanner;

public class Entrenador extends Usuario {
    private final long id;
    private final String nombre;
    private String nacionalidad;
    private boolean sesionIniciada = false;

    public Entrenador(long id, String nombre, String nacionalidad) {
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


    public static Entrenador crearEntrenador () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Nombre?");
        String nombreEntrenador = scanner.nextLine();
        System.out.println("Nacionalidades Admitidas");
        LectorXML.leerXML();
        System.out.println("¿Nacionalidad?");
        String nacionalidadEntrenador = scanner.nextLine();


        Entrenador entrenador = new Entrenador(2, nombreEntrenador, nacionalidadEntrenador);
        return entrenador;
    }
}
