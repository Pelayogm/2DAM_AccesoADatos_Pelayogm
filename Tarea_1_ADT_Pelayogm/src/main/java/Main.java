public class Main {
    public static void main (String [] args) {
        Boolean estado = Sesion.IniciarSesion();
        while (estado) {
           estado = Funciones.CerrarSesion();
        }
        System.out.println("Mucho trabajo por hoy");
    }
}
