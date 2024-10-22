import java.util.Scanner;

public class Funciones {
    private static boolean sesion = Sesion.isSesion();

    public static void MostrarFunciones () {

    }

    private static void CrearTorneo () {

    }

   public static boolean CerrarSesion () {
       Scanner scanner = new Scanner(System.in);
        if (sesion) {
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


}
