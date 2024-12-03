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

//ERRORES / REVISAR
//_____________________________________________
//ORDENAR CLASE SESION - CREAR CUENTA - (PRIMERO COMPROBAR USUARIO Y LUEGO CREAR CUENTA)
//ELIMINAR ENTRENADORES.DAT (Dejar de usarlo al empezar a usar la BD)
//DESLIAR EL CODIGO / Comentar el codigo
//ESTRUCTURAR EN PAQUETES (SEMI-COMPLETADO)
//REFACTORIZAR ENTRENADOR A ENTRENADORDTO (IDEA: se puede hacer herencia y admitir un objeto Entrenador y EntrenadorDTO)
//Corregir script de la BD
//TorneoDTO tiene listaDeParticipantes

//TAREA 2
//TorneoDTO lleva una ListaDeCombates que se tiene que hacer en torneoServices (COMPLETADA)
//ESTRUCTURA DAO (COMPLETADA)
//ESTRUCTURA ENTIDAD (COMPLETADA)
//ESTRUCTURA SERVICES (A MEDIAS)
//DUDAS
//_____________________________________________________________________________________
//setDouble sirve para Decimal en la BD?
//Los IDs de los usuarios (Admin - AdminsTorneos) que se hace con ellos, en la BD no se guardan?
//Que se hace Entrenador o EntrenadorDTO?
//Que hago con las tablasTorneos CombateTorneo?
//Exportar torneo como se hace? Como guardo al adminDeTorneos con su ID?
//Como hago la lista de participantes?

