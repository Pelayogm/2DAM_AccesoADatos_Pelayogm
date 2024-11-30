package proyectoPokemonADT;

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

//ELIMINAR ENTRENADORES.DAT
//DESLIAR EL CODIGO
//SOLO PONER EL NOMBRE DEL PAIS AL CREAR NUEVO USUARIO
//NO TIRAR EXCEPCION AL CREAR UN NUEVO ENTRENADOR Y EQUIVOCARSE EN EL PAIS
//CONVERTIR COSAS A SINGLETON (EJ CLASE EXPORTAR)
//ESTRUCTURAR EN PAQUETES

//REFACTORIZAR ENTRENADOR A ENTRENADORDTO
//TAREA 2

//REVISAR
//Estructura base de datos

//DUDAS
//El uso de los DTOs