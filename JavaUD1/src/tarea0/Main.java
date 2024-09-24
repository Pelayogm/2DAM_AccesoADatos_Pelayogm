package tarea0;

import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main (String [] args) throws Exception {
        //Cliente.nuevoCliente();
        NIF nif = new NIF();

        Date date = new Date();
        Cliente cliente = new Cliente(nif, "Fran", "Concordia5", "324242", date, 'E');
        cliente.anadirNotificacion(1, "6/5/2024");

        System.out.println(Arrays.toString(cliente.notificacionesEn(2024)));
    }
}
