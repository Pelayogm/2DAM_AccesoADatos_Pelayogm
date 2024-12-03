package proyectoPokemonADT;
import proyectoPokemonADT.ArchivosDelPrograma.ConexionBaseDeDatos;
import proyectoPokemonADT.Credenciales.Credenciales;
import proyectoPokemonADT.Administradores.*;
import proyectoPokemonADT.DTO.CombateDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Servicios.EntrenadoresServicio;
import proyectoPokemonADT.Servicios.TorneosServicio;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funciones {
    private static final ConexionBaseDeDatos conexionBaseDeDatos = ConexionBaseDeDatos.getInstancia();
    private static final DataSource dataSource = conexionBaseDeDatos.configurarDataSource();
    private static TorneosServicio torneosServicio = TorneosServicio.getInstancia(dataSource);

    private static ArrayList<Torneo> listTorneos = new ArrayList<>();
    private static ArrayList<String> credenciales = Credenciales.getCredenciales();

    public static void MostrarFunciones (Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        if (usuario instanceof Admin) {
            System.out.println("Bienvenido Administrador");
            System.out.println("1. Crear Torneo | 2. Cerrar Sesión");
            int adminOpcion = scanner.nextInt();
                while (adminOpcion < 4) {
                    if (adminOpcion == 1) {
                        Funciones.CrearTorneo(usuario);
                    }

                    if (adminOpcion == 2) {
                        Funciones.CerrarSesion(usuario);
                        break;
                    }

                    if (adminOpcion == 3) {
                        System.out.println("Hola");
                    }
                    System.out.println("1. Crear Torneo | 2. Cerrar Sesión");
                    adminOpcion = scanner.nextInt();
                }
        } else if (usuario instanceof Entrenador) {
            System.out.println("Hola " + usuario.getNombre());
            usuario.setEstadoSesion(true);
            Entrenador entrenador = (Entrenador) usuario;
            System.out.println("1. Exportar Carnet de Entrenador | 2. Cerrar Sesión");
            int userOpcion = scanner.nextInt();
            while (userOpcion < 4) {
                if (userOpcion == 1) {
                    Exportar.ExportarCarnet(entrenador);
                }

                if (userOpcion == 2) {
                    Funciones.CerrarSesion(entrenador);
                    break;
                }

                if (userOpcion == 3) {
                    System.out.println("Hola");
                }

                System.out.println("1. Exportar Carnet de Entrenador | 2. Cerrar Sesión");
                userOpcion = scanner.nextInt();
            }

        } else if (usuario instanceof AdminTorneos) {
            System.out.println("Hola Administrador de Torneos");
            System.out.println("1. Cerrar Sesión");
            int userOpcion = scanner.nextInt();
            while (userOpcion < 2) {
                if (userOpcion == 1) {
                    Funciones.CerrarSesion(usuario);
                    break;
                }
            }

        } else {
            System.out.println("No se reconoce el tipo de usuario");
        }
    }

    private static void CrearTorneo (Usuario usuario) {
        if (usuario.isUsuario()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Desea crear un nuevo Torneo?");
            System.out.println("1. Sí | 2. No");
            int adminOpcion = scanner.nextInt();
            File file_torneos = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Torneos.dat");
            try {
                if (!file_torneos.exists()) {
                    file_torneos.createNewFile();
                }

                try {
                    if (adminOpcion == 1) {
                        System.out.println("¿Nombre del torneo?");
                        String nombreTorneo = scanner.next();
                        System.out.println("¿Codigo del torneo?");
                        String regionTorneo = scanner.next();
                        char charRegion = regionTorneo.charAt(0);

                        System.out.println("¿Usuario del administrador de Torneos?");
                        String nombreAdminTorneos = scanner.next();
                        System.out.println("¿Contraseña del administrador de Torneos?");
                        String contrasenaAdminTorneos = scanner.next();

                        //SE CREAN UN FILE UNO PARA CREDENCIALES
                        File file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Credenciales.txt");
                        Credenciales.leerFichero(file);

                        if (!Credenciales.comprobarCredenciales(nombreAdminTorneos, contrasenaAdminTorneos)) {
                            //Creación del administrador de torneos en caso de que no exista en el fichero de credenciales.
                            int ultimoId = Integer.parseInt(credenciales.get(credenciales.size() - 1));
                            long idUsuario = ultimoId + 1;
                            AdminTorneos adminTorneos = new AdminTorneos(contrasenaAdminTorneos, nombreAdminTorneos, idUsuario);
                            String rolUsuario = "AdministradorTorneos";
                            String idUsuarioString = Long.toString(idUsuario);
                            Credenciales.escribirFichero(file, nombreAdminTorneos, contrasenaAdminTorneos, rolUsuario, idUsuarioString);
                            //Cuando todos los datos están listos se escribe el fichero de credenciales.

                            //Se crea un nuevo torneo.
                            Torneo torneo = new Torneo(listTorneos.size() + 1, nombreTorneo, charRegion);
                            torneo.setAdminTorneos(adminTorneos);
                            TorneoDTO torneoDTO = torneosServicio.mapearTorneoDTOaTorneo(torneo, adminTorneos.getIdUsuario());
                            torneosServicio.crearTorneo(torneoDTO);

                            GestorArchivosDat.exportarTorneo(file_torneos, torneo);
                            System.out.println("Torneo creado");
                            listTorneos.add(torneo);
                        } else {
                            //Esto sirve para sacar el id del Administrador de Torneos para crear el Torneo.
                            int idAdminTorneos = 0;
                            for (int i = 0; i < credenciales.size(); i++) {
                                if (credenciales.get(i).equals(nombreAdminTorneos) && credenciales.get(i + 1).equals(contrasenaAdminTorneos) && credenciales.get(i + 2).equals("AdministradorTorneos")) {
                                    idAdminTorneos = Integer.valueOf(credenciales.get(i + 3));
                                }
                            }
                            //Si el AdministradorDeTorneos ya estaba creado con anterioridad, se crea el torneo.
                            Torneo torneo = new Torneo(listTorneos.size() + 1, nombreTorneo, charRegion);
                            AdminTorneos adminTorneos = new AdminTorneos(contrasenaAdminTorneos, nombreAdminTorneos, idAdminTorneos);
                            torneo.setAdminTorneos(adminTorneos);

                            //Y ahora se añade a la BD el nuevo torneo creado.
                            TorneoDTO torneoDTO = torneosServicio.mapearTorneoDTOaTorneo(torneo, adminTorneos.getIdUsuario());
                            torneosServicio.crearTorneo(torneoDTO);

                            GestorArchivosDat.exportarTorneo(file_torneos, torneo);
                            System.out.println("Torneo creado");
                            listTorneos.add(torneo);
                        }

                    } else if (adminOpcion == 2) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Dato no valido");
                    }
                } catch (Exception e) {
                    System.out.println("Solo se admiten numeros");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No tienes los suficientes permisos como para acceder aqui");
        }
    }

 public static boolean CerrarSesion (Usuario usuario) {
          Scanner scanner = new Scanner(System.in);
            if (usuario.isEstadoSesion()) {
                System.out.println("SALIENDO DE LA SESION...");
                try {
                    System.out.println("1. Inciar Sesion | 2. Salir");
                    int opcionUsuario = scanner.nextInt();

                    if (opcionUsuario == 1) {
                        usuario.setEstadoSesion(false);
                        Sesion.IniciarSesion();
                   } else {
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println("Error en el cierre de sesión");
                }

           } else {
                System.out.println("No hay ninguna sesion iniciada");
               return false;

           }
         return false;
       }

    public static ArrayList<Torneo> getListTorneos() {
        return listTorneos;
    }
}
