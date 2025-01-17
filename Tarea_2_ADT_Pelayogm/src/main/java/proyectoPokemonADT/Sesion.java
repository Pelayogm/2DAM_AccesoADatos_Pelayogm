package proyectoPokemonADT;

import proyectoPokemonADT.Administradores.Admin;
import proyectoPokemonADT.Administradores.AdminTorneos;
import proyectoPokemonADT.ArchivosDelPrograma.ConexionBaseDeDatos;
import proyectoPokemonADT.Credenciales.Credenciales;
import proyectoPokemonADT.DTO.CarnetDTO;
import proyectoPokemonADT.DTO.EntrenadorDTO;
import proyectoPokemonADT.Servicios.EntrenadoresServicio;
import proyectoPokemonADT.Servicios.TorneosServicio;

import javax.sql.DataSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sesion {
    private static final ConexionBaseDeDatos conexionBaseDeDatos = ConexionBaseDeDatos.getInstancia();
    private static final DataSource dataSource = conexionBaseDeDatos.configurarDataSource();
    private static EntrenadoresServicio entrenadoresServicio = EntrenadoresServicio.getInstancia(dataSource);
    private static final TorneosServicio torneosServicio = TorneosServicio.getInstancia(dataSource);

    public static ArrayList<Usuario> listEntrenadores = new ArrayList<>();
    private static ArrayList<Torneo> listTorneos = Funciones.getListTorneos();
    private static ArrayList<String> listCredenciales = Credenciales.getCredenciales();

    public static Usuario IniciarSesion () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de la Calle Victoria");
        File file;
        try {
            System.out.println("¿Usuario?");
            String nombreEntrenador = scanner.nextLine();
                if (nombreEntrenador.isEmpty()) {
                    System.out.println("No se permiten campos vacíos");
                    IniciarSesion();
                }
            System.out.println("¿Contraseña?");
                String contrasena = scanner.nextLine();
                if (contrasena.isEmpty()) {
                    System.out.println("No se permiten campos vacíos");
                    IniciarSesion();
                }

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Credenciales.txt");

            //RELLENAR EL ARRAYLIST CON LOS DATOS QUE HAY EN EL CREDENCIALES.TXT
            Credenciales.leerFichero(file);

            //COMPROBAR EL ARRAYLIST DE CREDENCIALES PARA VER SI HAY COINCIDENCIAS
            try {
                if (Credenciales.comprobarCredenciales(nombreEntrenador, contrasena)) {
                    for (int i = 0; i < listCredenciales.size(); i++) {
                        if (nombreEntrenador.equals(listCredenciales.get(i)) && contrasena.equals(listCredenciales.get(i + 1)) && listCredenciales.get(i + 2).equals("Administrador")) {
                            Admin admin = new Admin(1);
                            Funciones.MostrarFunciones(admin);
                            return admin;
                        }

                        if (nombreEntrenador.equals(listCredenciales.get(i)) && contrasena.equals(listCredenciales.get(i + 1)) && listCredenciales.get(i + 2).equals("AdministradorTorneos")) {
                            int idAdminTorneos = Integer.parseInt(listCredenciales.get(i + 3));
                            AdminTorneos adminTorneos = new AdminTorneos(idAdminTorneos);
                            Funciones.MostrarFunciones(adminTorneos);
                            return adminTorneos;
                        }

                        if (nombreEntrenador.equals(listCredenciales.get(i)) && contrasena.equals(listCredenciales.get(i + 1)) && listCredenciales.get(i + 2).equals("Entrenador")) {
                          try {
                              int idUsuario = Integer.parseInt(listCredenciales.get(i + 3));
                              EntrenadorDTO entrenadorDTO = entrenadoresServicio.obtenerEntrenadorPorId(idUsuario);
                              Entrenador entrenador = entrenadoresServicio.mapearEntrenadorDtoAEntrenador(entrenadorDTO, entrenadorDTO.getCarnet());
                              Funciones.MostrarFunciones(entrenador);
                              return entrenador;
                          } catch (Exception e) {
                              System.out.println("Problemas aqui");
                          }
                        }
                    }

                } else {
                    System.out.println("No se han encontrado sus credenciales");

                    try {
                        System.out.println("¿Desea crear una cuenta?");
                        Scanner scanner_2 = new Scanner(System.in);
                        System.out.println("1. Sí quiero crear una cuenta | 2. No deseo crear una cuenta");
                        int numeroScanner = scanner_2.nextInt();
                        if (numeroScanner == 1) {
                            CrearCuenta();
                        } else {
                            System.out.println("Entendido no se creará una cuenta entonces.");
                        }
                    } catch (Exception e) {
                        System.out.println("Valor introducido no valido");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en la busqueda de datos");
            }
                } catch (Exception e) {
                    System.out.println("HA HABIDO UN ERROR EN LA COTEJACION DE DATOS");
                }

        return null;
    }

    public static void CrearCuenta () {
        System.out.println("Registrarse en la Calle Victoria");
        Scanner scanner = new Scanner(System.in);
        File file;
        try {
            //FORMULARIO PARA EL USUARIO
            System.out.println("¿Usuario?");
            String nombreUsuario = scanner.nextLine();
            System.out.println("¿Contraseña?");
            String constrasenaUsuario = scanner.nextLine();
            System.out.println("A continuacion va a se va a crear una cuenta en el club de Entrenadores Pokemon");
            //SE PREPARA UN ARCHIVO PARA ESCRIBIR EL ENTRENDADOR EN UN ARCHIVO DAT Y UNO PARA LEER LOS TORNEOS
            //File file_torneos = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Torneos.dat");
            //GestorArchivosDat.cargarTorneo(file_torneos);
            //ComprobacionTorneos();

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Credenciales.txt");
            Credenciales.leerFichero(file);
            Entrenador entrenador = new Entrenador();

            try {
                if (Credenciales.comprobarCredenciales(nombreUsuario, constrasenaUsuario)) {
                    Scanner scanner_2 = new Scanner(System.in);
                    System.out.println("El usuario y contraseña introducido ya existe");
                    System.out.println("¿Desea iniciar sesión? - 1. Si | 2. No");
                    try {
                        int opcionUsuario = scanner_2.nextInt();
                        switch (opcionUsuario) {
                            case 1 -> Sesion.IniciarSesion();
                            case 2 -> System.out.println("Adios");
                        }
                    } catch (Exception e) {
                        System.out.println("Valor no válido");
                    }
                } else {
                        try {
                            //Cogemos el ultimo id del fichero de credenciales
                            int ultimoId = Integer.parseInt(listCredenciales.get(listCredenciales.size() - 1));
                            long idUsuario = ultimoId + 1;
                            //Creamos el rol del usuario.
                            String rolUsuario = "Entrenador";
                            //Pasamos el id a String para escribirlo
                            String idUsuarioString = Long.toString(idUsuario);

                            //SE LLAMA A CREAR ENTRENADOR
                            entrenador = Entrenador.crearEntrenador(nombreUsuario, idUsuario);
                            if (entrenador != null) {
                                //Creamos el carnet del entrenador
                                CarnetDTO carnet = new CarnetDTO(idUsuario, entrenador.getCarnet().getFechaExpedicion(), entrenador.getCarnet().getPuntos(), entrenador.getCarnet().getNumVictorias());
                                //Mapeamos el objeto entrenador y su carnet que obtuvimos durante el registro
                                EntrenadorDTO entrenadorDto = entrenadoresServicio.mapearEntrenadorAEntrenadorDto(entrenador, carnet);
                                //Se crea el entrenador en la BD
                                entrenadoresServicio.crearEntrenador(entrenadorDto);
                                //Añadimos el idDelEntrenador a la tabla Entrenador_Torneo después de añadir el entrenador a la BD, para evitar errores de que estamos
                                //añandiendo un ID que no existe.
                                Torneo torneo = entrenador.getTorneosDelEntrenador().get(0);
                                torneosServicio.actualizarParticipantesTorneo(torneo.getId(), entrenadorDto.getId());
                                //Añadimos el entrenador al fichero de credenciales
                                Credenciales.escribirFichero(file, nombreUsuario, constrasenaUsuario, rolUsuario, idUsuarioString);
                                //Informamos del éxito de la creación de cuenta
                                System.out.println("Cuenta creada con éxito");
                                //Mostramos las funciones disponibles al entrenador
                                Funciones.MostrarFunciones(entrenador);
                            } else {
                                System.exit(1);
                            }
                        } catch (Exception e) {
                            System.out.println("No esta disponible este servicio por el momento");
                            System.out.println("Informese de si existe algún torneo para registrarse");
                        }
                    }

            } catch (Exception e) {
                System.out.println("No se ha podido encontrar el fichero de credenciales");
            }
        } catch (Exception e) {
            System.out.println("No se ha podido comprobar las credenciales");
        }
    }

    public static ArrayList<Usuario> getListEntrenadores() {
        return listEntrenadores;
    }
}
