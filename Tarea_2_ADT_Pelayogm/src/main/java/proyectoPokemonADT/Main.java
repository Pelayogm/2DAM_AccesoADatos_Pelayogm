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
//ORDENAR CLASE SESION - CREAR CUENTA - (PRIMERO COMPROBAR USUARIO Y LUEGO CREAR CUENTA) - (COMPLETADO) - REVISAR CON LA BD
//ELIMINAR ENTRENADORES.DAT (COMPLETADO)
//DESLIAR EL CODIGO / Comentar el codigo
//ESTRUCTURAR EN PAQUETES (SEMI-COMPLETADO)
//Corregir script de la BD
//Al crear un torneo vacio, los idDeLos participantes son 0, y no hay entrenador con id 0 (se soluciona con Entrenador Default)
//La tabla TorneoAdmin (Revisar)


//TAREA 2
//TorneoDTO lleva una ListaDeCombates que se tiene que hacer en torneoServices (COMPLETADA)
//ESTRUCTURA DAO (COMPLETADA)
//Coger datos del torneo en la base de datos (Apuntarse a un torneo)

//DUDAS
//_____________________________________________________________________________________
//Enseñar el error de crear 2 torneos seguidos

//El entrenador lleva una lista de torneos. (Hacer Tabla TorneoEntrenador)
//TorneoDTO tiene listaDeParticipantes
//El torneo se exporta en .txt y se sale por pantalla y se guarda el id del torneo Creado con el adminDeTorneos.
//En un torneo un entrenador solo puede combatir 2 veces y en un torneo hay 3 combates.
