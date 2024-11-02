import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Entrenador extends Usuario implements Serializable {
    private long id;
    private String nombre;
    private String nacionalidad;
    private final boolean esEntrenador = true;
    private Carnet carnet;

    //LISTA DE TORNEOS PARA VER SI HAY ALGUNO ACTIVO
    private static ArrayList <Torneo> torneos = Funciones.getListTorneos();
    private static ArrayList<Torneo> torneosDelEntrenador = new ArrayList<>();

    public ArrayList<Torneo> getTorneosDelEntrenador() {
        return torneosDelEntrenador;
    }

    public boolean isEsEntrenador() {
        return esEntrenador;
    }

    public Entrenador() {
    }

    public Carnet getCarnet() {
        return carnet;
    }

    private LocalDate fechaActual = LocalDate.now();

    public Entrenador(long id, String nombre, String nacionalidad) {
            setEstadoSesion(true);
            setUsuario(false);
            setNombreUsuario(nombre);
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = new Carnet(id, fechaActual);
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

    public static Entrenador crearEntrenador (String nombreUsuario) {
        Scanner scanner_entrenador = new Scanner(System.in);
        System.out.println("¿Nombre?");
        System.out.println("Nombre elegido: " + nombreUsuario);
        System.out.println("Nacionalidades Admitidas");
        LectorXML.leerXML();
        System.out.println("¿Nacionalidad?");
        String nacionalidadEntrenador = scanner_entrenador.nextLine();

        //SI LA NACIONALIDAD QUE ESTA EN PAISES.XML ESTA BIEN
        if (LectorXML.comprobarNacionalidadConXML(nacionalidadEntrenador)) {
            //SE CREA EL OBJETO ENTRENADOR
            Entrenador entrenador = new Entrenador(2, nombreUsuario, nacionalidadEntrenador);

            //SE RECORRE LA LISTA DE TORNEOS EXISTENTES EN LA SESION
            for (int i = 0; i < torneos.size(); i++) {
                //SI HAY UNO CREADO
                if (torneos.get(i).isTorneoCreado()) {
                    //SE AÑADE A LISTA DE TORNEOS QUE TIENE EL ENTRENADOR EL TORNEO QUE SE ENCUENTRE ACTIVO
                    torneosDelEntrenador.add(torneos.get(i));
                    //PROCEDIMIENTO DE ENTRADA AL CREAR LA CUENTA
                    System.out.println("Bienvenido: " + nombreUsuario);
                    System.out.println("Estas apuntado en el torneo " + torneos.get(i).getNombre());

                    //SE AÑADE EL ENTRENADOR A LA LISTA DE PARTICIPANTES QUE TIENE EL TORNEO
                    torneos.get(i).getParticipantesDelTorneo().add(entrenador);
                    return entrenador;
                }
            }

        } else {
            System.out.println("Datos mal introducidos");
        }
        return null;
    }
}
