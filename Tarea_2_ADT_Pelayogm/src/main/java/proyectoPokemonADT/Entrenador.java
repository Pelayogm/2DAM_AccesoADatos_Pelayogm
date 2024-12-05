package proyectoPokemonADT;
import proyectoPokemonADT.ArchivosDelPrograma.ConexionBaseDeDatos;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Servicios.EntrenadoresServicio;
import proyectoPokemonADT.Servicios.TorneosServicio;
import proyectoPokemonADT.XML.*;

import javax.sql.DataSource;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entrenador extends Usuario implements Serializable {
    private long id;
    private String nombre;
    private String nacionalidad;
    private final boolean esEntrenador = true;
    private Carnet carnet;

    private static final ConexionBaseDeDatos conexionBaseDeDatos = ConexionBaseDeDatos.getInstancia();
    private static final DataSource dataSource = conexionBaseDeDatos.configurarDataSource();
    private static final TorneosServicio torneosServicio = TorneosServicio.getInstancia(dataSource);
    private static final EntrenadoresServicio entrenadoresServicio = EntrenadoresServicio.getInstancia(dataSource);

    //ARRAYLIST DE LOS TORNEOS EN LOS QUE ESTA O ESTUVO PRESENTE
    private ArrayList<Torneo> torneosDelEntrenador = new ArrayList<>();
    //FECHA PARA PASARSELA AL CONSTRUCTOR
    private LocalDate fechaActual = LocalDate.now();

    public Entrenador() {
    }

    public Entrenador(long id, String nombre, String nacionalidad) {
            setEstadoSesion(true);
            setUsuario(false);
            setNombreUsuario(nombre);
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = new Carnet(id, fechaActual);
    }

    //Constructor utilizado para los mapeos de Entrenador a EntrenadorDTO y viceversa.
    public Entrenador(long id, String nombre, String nacionalidad, Carnet carnet) {
        setEstadoSesion(true);
        setUsuario(false);
        setNombreUsuario(nombre);
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
    }

    //Metodo de crear entrenador en caso de nuevo registro
    public static Entrenador crearEntrenador (String nombreUsuario, long id) {
        Scanner scanner_entrenador = new Scanner(System.in);
        System.out.println("¿Nombre?");
        System.out.println("Nombre elegido: " + nombreUsuario);
        System.out.println("Nacionalidades Admitidas");
        LectorXML.leerXML();
        System.out.println("¿Nacionalidad?");
        String nacionalidadEntrenador = scanner_entrenador.nextLine();

        System.out.println("¿La información es correcta? 1. Sí | 2. No");
        int verificarInformacion = scanner_entrenador.nextInt();
        if (verificarInformacion == 2) {
            Entrenador.crearEntrenador(nombreUsuario, id);
        }

        //Se comprueba la nacionalidad introducida por pantalla, con el metodo de la clase LectorXML.
        if (LectorXML.comprobarNacionalidadConXML(nacionalidadEntrenador)) {
            //Si la nacionalidad es correcta creamos un objeto entrenador
            Entrenador entrenador = new Entrenador(id, nombreUsuario, nacionalidadEntrenador);
            List<TorneoDTO> listaDeTodosLosTorneos = torneosServicio.obtenerTodosLosTorneos();
            if (listaDeTodosLosTorneos.isEmpty()) {
                System.out.println("Actualmente no hay ningún torneo disponible, espere a que haya un nuevo torneo");
                return null;
            }

            System.out.println("Todos los torneos");
            //Con la anterior condición filtramos si existen torneos.
            for (int i = 0; i < listaDeTodosLosTorneos.size(); i++) {
                System.out.println(i + " - " + listaDeTodosLosTorneos.get(i).getNombre());
            }
            System.out.println("Indique el numero del torneo que desee");
            int torneoUsuario = scanner_entrenador.nextInt();
            List<Integer> participantes = entrenadoresServicio.listaDeParticipantes(listaDeTodosLosTorneos.get(torneoUsuario).getId());
            if (participantes.size() < 3) {
                TorneoDTO torneoDto = listaDeTodosLosTorneos.get(torneoUsuario);
                Torneo torneo = torneosServicio.mapearDtoATorneo(torneoDto);
                entrenador.torneosDelEntrenador.add(torneo);
                System.out.println("Bienvenido: " + nombreUsuario);
                System.out.println("Estas apuntado en el torneo " + torneo.getNombre());
                return entrenador;
            } else {
                System.out.println("El torneo elegido esta lleno");
                return null;
            }
            /*
                    Torneo torneo = new Torneo(0, "Inicial", 'I', 100);
                    entrenador.torneosDelEntrenador.add(torneo);
                    //PROCEDIMIENTO DE ENTRADA AL CREAR LA CUENTA


                    //SE AÑADE EL ENTRENADOR A LA LISTA DE PARTICIPANTES QUE TIENE EL TORNEO
                    //torneos.get(i).getParticipantesDelTorneo().add(entrenador);
                    return entrenador;
                //}*/
        } else {
            System.out.println("Datos mal introducidos");
        }
        return null;
    }


    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public ArrayList<Torneo> getTorneosDelEntrenador() {
        return torneosDelEntrenador;
    }

    public boolean isEsEntrenador() {
        return esEntrenador;
    }

    public void setTorneosDelEntrenador(ArrayList<Torneo> torneosDelEntrenador) {
        this.torneosDelEntrenador = torneosDelEntrenador;
    }

}
