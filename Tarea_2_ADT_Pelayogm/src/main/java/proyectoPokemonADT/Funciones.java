package proyectoPokemonADT;
import proyectoPokemonADT.ArchivosDelPrograma.ConexionBaseDeDatos;
import proyectoPokemonADT.Credenciales.Credenciales;
import proyectoPokemonADT.Administradores.*;
import proyectoPokemonADT.DTO.CombateDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Servicios.CombateServicio;
import proyectoPokemonADT.Servicios.TorneosServicio;

import javax.sql.DataSource;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funciones {
    private static final ConexionBaseDeDatos conexionBaseDeDatos = ConexionBaseDeDatos.getInstancia();
    private static final DataSource dataSource = conexionBaseDeDatos.configurarDataSource();
    private static final TorneosServicio torneosServicio = TorneosServicio.getInstancia(dataSource);
    private static final CombateServicio combateServicio = CombateServicio.getInstancia(dataSource);

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
            System.out.println("1. Exportar Carnet de Entrenador | 2. Apuntarse a un torneo | 3. Cerrar Sesión");
            int userOpcion = scanner.nextInt();
            while (userOpcion < 5) {
                if (userOpcion == 1) {
                    Exportar.ExportarCarnet(entrenador);
                }

                if (userOpcion == 2) {
                    int id = (int) entrenador.getId();
                    GestionDeTorneos.apuntarseAUnTorneo(id);
                }

                if (userOpcion == 3) {
                    Funciones.CerrarSesion(entrenador);
                    break;
                }

                if (userOpcion == 4) {
                    System.out.println("Hola");
                }

                System.out.println("1. Exportar Carnet de Entrenador | 3. Cerrar Sesión");
                userOpcion = scanner.nextInt();
            }

        } else if (usuario instanceof AdminTorneos) {
            System.out.println("Hola Administrador de Torneos");
            System.out.println("1. Exportar Torneo | 2. Cerrar Sesión");
            usuario.setEstadoSesion(true);
            int userOpcion = scanner.nextInt();
            while (userOpcion < 3) {
                if (userOpcion == 1) {
                    GestionDeTorneos.exportarTorneo(usuario);
                }

                if (userOpcion == 2) {
                    Funciones.CerrarSesion(usuario);
                    break;
                }
                System.out.println("1. Exportar Torneo | 2. Cerrar Sesión");
                userOpcion = scanner.nextInt();
            }

        } else {
            System.out.println("No se reconoce el tipo de usuario");
        }
    }

    private static void CrearTorneo (Usuario usuario) {
        List<TorneoDTO> listaDeTorneos = torneosServicio.obtenerTodosLosTorneos();
        List<CombateDTO> listaDeTodosLosCombate = combateServicio.obtenerTodosLosCombates();

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

                        System.out.println("¿Son correctos estos datos? 1. Sí | 2. No");
                        int opcionUsuario = scanner.nextInt();
                        if (opcionUsuario == 2) {
                            Funciones.CrearTorneo(usuario);
                        }

                        //Se crea el archivo de credenciales para guardar el administrador de torneos en caso de que no estuviera creado.
                        File file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Credenciales.txt");
                        //Cargamos los datos del fichero
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
                            Torneo torneo = new Torneo(listaDeTorneos.size() + 1, nombreTorneo, charRegion);
                            torneo.setAdminTorneos(adminTorneos);
                            //Para calcular los idDeLosCombates sacamos la longitud de la lista de todos los combates de la BD.
                            int idCombate = listaDeTodosLosCombate.size();

                            int valor;
                            if (listaDeTorneos.isEmpty()) {
                                valor = 1;
                            } else {
                                valor = listaDeTorneos.size() + 1;
                            }

                            //Se crean los 3 combates del Torneo, vacios.
                            ArrayList<CombateDTO> combatesDelTorneo = torneo.getCombatesDelTorneo();
                            for (int i = 1; i < 4; i++) {
                                CombateDTO combate = new CombateDTO(LocalDate.now(),idCombate + 1, valor);
                                combatesDelTorneo.add(combate);
                            }
                            torneo.setCombatesDelTorneo(combatesDelTorneo);

                            //Y esto sirve para convertir el objeto Torneo en DTO y interactuar con la BD.
                            TorneoDTO torneoDTO = torneosServicio.mapearTorneoATorneoDto(torneo, adminTorneos.getIdUsuario());
                            torneosServicio.crearTorneo(torneoDTO);

                            GestorArchivosDat.exportarTorneo(file_torneos, torneo);
                            System.out.println("Torneo creado");
                            listTorneos.add(torneo);
                        } else {
                            //Si el admin de torneos ya está creado se pasa por aquí.
                            //Esto sirve para sacar el id del Administrador de Torneos para crear el Torneo.
                            int idAdminTorneos = 0;
                            for (int i = 0; i < credenciales.size(); i++) {
                                if (credenciales.get(i).equals(nombreAdminTorneos) && credenciales.get(i + 1).equals(contrasenaAdminTorneos) && credenciales.get(i + 2).equals("AdministradorTorneos")) {
                                    idAdminTorneos = Integer.valueOf(credenciales.get(i + 3));
                                }
                            }
                            //Si el AdministradorDeTorneos ya estaba creado con anterioridad, se crea el torneo.
                            Torneo torneo = new Torneo(listaDeTorneos.size() + 1, nombreTorneo, charRegion);
                            AdminTorneos adminTorneos = new AdminTorneos(contrasenaAdminTorneos, nombreAdminTorneos, idAdminTorneos);
                            torneo.setAdminTorneos(adminTorneos);
                            //Para calcular los idDeLosCombates sacamos la longitud de la lista de todos los combates de la BD.
                            int idCombate = listaDeTodosLosCombate.size();
                            //Se crean los 3 combates del Torneo, vacios.
                            ArrayList<CombateDTO> combatesDelTorneo = torneo.getCombatesDelTorneo();

                            int valor;
                            if (listaDeTorneos.isEmpty()) {
                                valor = 1;
                            } else {
                                valor = listaDeTorneos.size() + 1;
                            }

                            for (int i = 1; i < 4; i++) {
                                CombateDTO combate = new CombateDTO(LocalDate.now(), idCombate + i, valor);
                                combatesDelTorneo.add(combate);
                            }
                            torneo.setCombatesDelTorneo(combatesDelTorneo);

                            //Y ahora se añade a la BD el nuevo torneo creado.
                            TorneoDTO torneoDTO = torneosServicio.mapearTorneoATorneoDto(torneo, adminTorneos.getIdUsuario());
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
