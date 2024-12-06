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
            System.out.println("Solo números");
        }
    }
}

//En casa es root-root y en clase es root-

//ERRORES / REVISAR
//_____________________________________________
//DESLIAR EL CÓDIGO / Comentar el código
//ESTRUCTURAR EN PAQUETES (SEMI-COMPLETADO)
//Corregir script de la BD

//TAREA 2
//TorneoDTO lleva una ListaDeCombates que se tiene que hacer en torneoServices (COMPLETADA)
//ESTRUCTURA DAO (COMPLETADA)
//Coger datos del torneo en la base de datos (Apuntarse a un torneo) (COMPLETADO)

//DUDAS
//_____________________________________________________________________________________
//El entrenador lleva una lista de torneos. (Hacer Tabla TorneoEntrenador), para el torneo inicial. (COMPLETADO)
//Hacer que se reflejen los torneos en la bd y exportar carnet (COMPLETADO)

//Al entrenador se le enseñan los torneos disponibles y escoge el que quiere (COMPLETADO)
//Y el administrador de torneos al hacer login se le enseñan todos los torneos que administra y exporta el que quiera. (COMPLETADO)
//El torneo se exporta en .txt y se sale por pantalla y se guarda el id del torneo Creado con el adminDeTorneos. (COMPLETADO)

//En un torneo un entrenador solo puede combatir 2 veces y en un torneo hay 3 combates. (COMPLETADO - REVISAR)
//Hacer flush en el exportar
//Añadir los combates al XML (COMPLETADO)

//TorneoDTO tiene listaDeParticipantes


