import java.util.Scanner;

public class Main {
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Entrenadores Pokemon");
        System.out.println("Seleccione una opcion");
        System.out.println("1. Iniciar Sesion | 2. Crear cuenta | 3. Salir");
        int opcion = scanner.nextInt();
        try {
            switch (opcion) {
                case 1 -> Sesion.IniciarSesion();
                case 2 -> Sesion.CrearCuenta();
                case 3 -> System.out.println("ADIOS");
            }
        } catch (Exception e) {
            System.out.println("Solo numeros");
        }
    }
}

//EL DAT NO RECONOCE 2 ENTRENADORES AL MISMO TIEMPO, PISA UNO A OTRO
//ARREGLAR MÉTODO PARA COMPROBAR SI ES ENTRENADOR O NO EN CREAR TORNEO
//EL DAT NO GUARDA UN ARRAYLIST CON TORNEOS A PESAR DE QUE TORNEO ES SERIALIZABLE
