import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Funciones {
    private static ArrayList<Torneo> listTorneos = new ArrayList<>();

    public static void MostrarFunciones (Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        if (usuario.isUsuario()) {
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
        } else {
            System.out.println("Hola " + usuario.getNombre());
            System.out.println("1. Exportar Carnet de Entrenador | 2. Cerrar Sesión");
            int userOpcion = scanner.nextInt();
            while (userOpcion < 4) {
                if (userOpcion == 1) {
                    Exportar.ExportarCarnet();
                }

                if (userOpcion == 2) {
                    Funciones.CerrarSesion(usuario);
                    break;
                }

                if (userOpcion == 3) {
                    System.out.println("Hola");

                }
                System.out.println("1. Exportar Carnet de Entrenador | 2. Cerrar Sesión");
                userOpcion = scanner.nextInt();
            }

        }
    }

    private static void CrearTorneo (Usuario usuario) {
        if (usuario.isUsuario()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Desea crear un nuevo Torneo?");
            System.out.println("1. Sí | 2. No");
                int adminOpcion = scanner.nextInt();
                try {
                    if (adminOpcion == 1) {
                        System.out.println("¿Nombre del torneo?");
                            String nombreTorneo = scanner.next();
                        System.out.println("¿Codigo del torneo?");
                            String regionTorneo = scanner.next();
                            char charRegion = regionTorneo.charAt(0);

                            //PENDIENTE CREAR ADMIN TORNEOS
                        //COMPROBAR QUE YA EXISTA HACER METODOS DESTINADOS
                        StringBuilder stringBuilder = new StringBuilder();
                        System.out.println("¿Usuario del administrador de Torneos?");
                            String nombreAdminTorneos = scanner.next();
                            stringBuilder.append(nombreAdminTorneos);
                            stringBuilder.append("-");
                        System.out.println("¿Contraseña del administrador de Torneos?");
                            String contrasenaAdminTorneos = scanner.next();
                            stringBuilder.append(contrasenaAdminTorneos);
                            String credenciales = stringBuilder.toString();

                        File file = new File(".", "Credenciales.txt");
                        Credenciales.escribirFichero(file, credenciales);

                             Torneo torneo = new Torneo(listTorneos.size() + 1,nombreTorneo, charRegion);
                             listTorneos.add(torneo);

                    } else if (adminOpcion == 2) {
                        System.out.println("Saliendo...");
                    } else {
                        System.out.println("Dato no valido");
                    }
                } catch (Exception e) {
                    System.out.println("Solo se admiten numeros");
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
