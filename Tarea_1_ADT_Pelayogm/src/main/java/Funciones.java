import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Funciones {
    private static ArrayList<Torneo> listTorneos = new ArrayList<>();
    private static ArrayList<Usuario> listUsuarios = Sesion.getListEntrenadores();

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
            System.out.println("Número de torneos: " + entrenador.getTorneosDelEntrenador().size());
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
            File file_torneos = new File(".", "Torneos.dat");
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

                        StringBuilder stringBuilder = new StringBuilder();
                        System.out.println("¿Usuario del administrador de Torneos?");
                        String nombreAdminTorneos = scanner.next();
                        stringBuilder.append(nombreAdminTorneos);
                        stringBuilder.append("-");
                        System.out.println("¿Contraseña del administrador de Torneos?");
                        String contrasenaAdminTorneos = scanner.next();
                        stringBuilder.append(contrasenaAdminTorneos);
                        String credenciales = stringBuilder.toString();

                        //SE CREAN 2 FILE UNO PARA CREDENCIALES Y OTRO PARA COMPROBAR
                        File file = new File(".", "Credenciales.txt");
                        File file_dat = new File(".", "Usuarios.dat");

                        File fileCredenciales = new File(".", "Credenciales.txt");
                        Credenciales.leerFichero(fileCredenciales);

                        //if (!GestorArchivosDat.comprobarEntrenadorDat(file_dat, nombreAdminTorneos)) {
                        //if (file_dat != null && file.length() != 0) {
                        //if (!GestorArchivosDat.comprobarEntrenadorDat(file_dat, nombreAdminTorneos)) {
                        if (!Credenciales.comprobarCredenciales(credenciales)) {
                            long idUsuario = Credenciales.getContadorLineas() / 3;
                            AdminTorneos adminTorneos = new AdminTorneos(contrasenaAdminTorneos, nombreAdminTorneos, idUsuario);
                            GestorArchivosDat.escribirEntrenadoresDat(file_dat, adminTorneos);
                            String rolUsuario = "AdministradorTorneos";

                            String idUsuarioString = Long.toString(idUsuario);
                            Credenciales.escribirFichero(file, credenciales, rolUsuario, idUsuarioString);
                            Torneo torneo = new Torneo(listTorneos.size() + 1, nombreTorneo, charRegion);
                            torneo.setAdminTorneos(adminTorneos);
                            GestorArchivosDat.exportarTorneo(file_torneos, torneo);
                            System.out.println("Torneo creado");
                            listTorneos.add(torneo);
                        } else {
                            //SI YA ESTA EN LA LISTA DE CREDENCIALES SE CREA EL TORNEO Y SE AÑADE A LA LISTA
                            Torneo torneo = new Torneo(listTorneos.size() + 1, nombreTorneo, charRegion);
                            AdminTorneos adminTorneos = new AdminTorneos(contrasenaAdminTorneos, nombreAdminTorneos);
                            torneo.setAdminTorneos(adminTorneos);
                            GestorArchivosDat.exportarTorneo(file_torneos, torneo);
                            System.out.println("Torneo creado");
                            listTorneos.add(torneo);
                        }
                        //} else {
                        // System.out.println("El usuario introducido es un entrenador");
                        //}
                        //}
                        //} else {
                        //System.out.println("El usuario introducido es un Entrenador, vuelve a intentarlo con otro usuario");
                        //}

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
