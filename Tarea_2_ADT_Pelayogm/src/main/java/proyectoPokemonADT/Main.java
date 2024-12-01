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

//ORDENAR SESION - CREAR CUENTA - PRIMERO COMPROBAR USUARIO Y LUEGO CREAR CUENTA

//ELIMINAR ENTRENADORES.DAT (Dejar de usarlo al empezar a usar la BD)
//DESLIAR EL CODIGO
//CONVERTIR COSAS A SINGLETON (EJ CLASE EXPORTAR)
//ESTRUCTURAR EN PAQUETES

//REFACTORIZAR ENTRENADOR A ENTRENADORDTO

//TAREA 2
//TorneoDTO lleva una ListaDeCombates que se tiene que hacer en torneoServices
//Los IDs de los usuarios (Admin - AdminsTorneos) que se hace con ellos, en la BD no se guardan
//Que se hace Entrenador o EntrenadorDTO
//Que hago con las tablasTorneos CombateTorneo

//ESTRUCTURA DAO (COMPLETADA)
//TorneoDTO tiene listaDeParticipantes
//Corregir script de la BD
