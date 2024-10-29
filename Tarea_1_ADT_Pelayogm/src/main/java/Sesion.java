import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Sesion {
    private static ArrayList<String> credenciales = new ArrayList<>();
    public static ArrayList<Entrenador> listEntrenadores = new ArrayList<>();
    private static ArrayList<Torneo> listTorneos = Funciones.getListTorneos();

    private static boolean flag = false;

    public static void ComprobacionTorneos () {
        for (int i = 0; i < listTorneos.size(); i++) {
            if (listTorneos.get(i).isTorneoCreado()) {
                flag = true;
            }
        }
    }

    public static Usuario IniciarSesion () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de la Calle Victoria");
        File file;
        boolean usuarioExiste = false;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("¿Usuario?");
            String nombreEntrenador = scanner.nextLine();
                stringBuilder.append(nombreEntrenador);
                if (stringBuilder.isEmpty()) {
                    System.out.println("No se permiten campos vacíos");
                    IniciarSesion();
                }
                stringBuilder.append("-");
            System.out.println("¿Contraseña?");
                String contrasena = scanner.nextLine();
                if (contrasena.isEmpty()) {
                    System.out.println("No se permiten campos vacíos");
                    IniciarSesion();
                }
                stringBuilder.append(contrasena);
            String datosIntroducidosUsuario = stringBuilder.toString();

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File(".", "Credenciales.txt");
            //RELLENAR EL ARRAYLIST CON LOS DATOS QUE HAY EN EL CREDENCIALES.TXT
            Credenciales.leerFichero(file);

            //COMPROBAR EL ARRAYLIST DE CREDENCIALES PARA VER SI HAY COINCIDENCIAS
                try {
                    for (int i = 0; i < credenciales.size(); i++) {
                        if (credenciales.get(i).equals(datosIntroducidosUsuario)) {
                            if (datosIntroducidosUsuario.equals("admin-admin")) {
                                Admin admin = new Admin(1);
                                Funciones.MostrarFunciones(admin);
                                return admin;
                            } else {
                                Entrenador entrenador;
                                File file_entrenadores = new File(".", "Entrenadores.dat");
                                try {
                                    GestorArchivosDat.leerEntrenadoresDat(file_entrenadores);
                                    for (int is = 0; is < listEntrenadores.size(); is++) {
                                        if (listEntrenadores.get(is).getNombre().equals(nombreEntrenador)) {
                                            entrenador = listEntrenadores.get(is);
                                            System.out.println("Sesion Iniciada");
                                            Funciones.MostrarFunciones(entrenador);
                                            return entrenador;
                                        }
                                    }
                                } catch (Exception e) {
                                    System.out.println("Problema con el archivo DAT");
                                }
                            }
                        }
                    }
                        if (!usuarioExiste) {
                            System.out.println("DATOS NO ENCONTRADOS EN EL SISTEMA");
                            System.out.println("¿Desea crear una cuenta?");
                            Scanner scanner_2 = new Scanner(System.in);
                            System.out.println("1. Sí quiero crear una cuenta | 2. No deseo crear una cuenta");
                            int numeroScanner = scanner_2.nextInt();
                            if (numeroScanner == 1) {
                                CrearCuenta();
                            } else {
                                System.out.println("Entendido no se creará una cuenta entonces.");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Valor fuera de los rangos");
                    }
                } catch (Exception e) {
                    System.out.println("HA HABIDO UN ERROR EN LA COTEJACION DE DATOS");
                }

        return null;
    }

    public static void CrearCuenta () {
        System.out.println("Registrarse en la Calle Victoria");
        Scanner scanner = new Scanner(System.in);
        File file;
        boolean usuarioExiste = false;
        try {
            //FORMULARIO PARA EL USUARIO
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("¿Usuario?");
            String nombreUsuario = scanner.nextLine();
            stringBuilder.append(nombreUsuario);
            stringBuilder.append("-");
            System.out.println("¿Contraseña?");
            stringBuilder.append(scanner.nextLine());
            String datosIntroducidosUsuario = stringBuilder.toString();
            System.out.println("A continuacion va a se va a crear una cuenta en el club de Entrenadores Pokemon");

            //SE PREPARA UN ARCHIVO PARA ESCRIBIR EL ENTRENDADOR EN UN ARCHIVO DAT
            File file_escribirdatos;
            ComprobacionTorneos();
                if (flag) {
                    try {
                        file_escribirdatos = new File(".", "Entrenadores.dat");
                        //SE LLAMA A CREAR ENTRENADOR
                        Entrenador entrenador = Entrenador.crearEntrenador(nombreUsuario);
                        //CON LO QUE RETORNA CREAR ENTRENADOR ESCRIBIMOS EL DAT
                        GestorArchivosDat.escribirEntrenadoresDat(file_escribirdatos, entrenador);
                    } catch (Exception e) {
                        System.out.println("Error con el DAT");
                    }
                } else {
                    System.out.println("No esta disponible este servicio por el momento");
                }

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File(".", "Credenciales.txt");
            //RELLENAR EL ARRAYLIST CON LOS DATOS QUE HAY EN EL CREDENCIALES.TXT
            Credenciales.leerFichero(file);

            //COMPROBAR EL ARRAYLIST DE CREDENCIALES PARA VER SI HAY COINCIDENCIAS
            try {
                for (int i = 0; i < credenciales.size(); i++) {
                    if (credenciales.get(i).equals(datosIntroducidosUsuario)) {
                        usuarioExiste = true;
                        System.out.println("YA EXISTE UNA CUENTA CON ESTOS DATOS");
                        try {
                            Scanner scanner_2 = new Scanner(System.in);
                            System.out.println("¿Desea iniciar sesion en la calle Victoria?");
                            System.out.println("1. Si | 2. No");
                            int opcionUser = scanner_2.nextInt();
                            if (opcionUser == 1) {
                                Sesion.IniciarSesion();
                            } else {
                                System.out.println("Hasta pronto");
                            }
                        } catch (Exception e) {
                            System.out.println("Valor fuera de rangos");
                        }
                    }
                }

                if (!usuarioExiste && flag) {
                    Credenciales.escribirFichero(file, datosIntroducidosUsuario);
                    System.out.println("Cuenta creada con éxito");
                    try {
                        Scanner scanner_3 = new Scanner(System.in);
                        System.out.println("¿Desea iniciar sesion en la calle Victoria?");
                        System.out.println("1. Si | 2. No");
                        int opcionUser = scanner_3.nextInt();
                        if (opcionUser == 1) {
                            Sesion.IniciarSesion();
                        } else {
                            System.out.println("Hasta pronto");
                        }
                    } catch (Exception e) {
                        System.out.println("Valor fuera de rangos");
                    }
                }
            } catch (Exception e) {
                System.out.println("No se ha podido completar la creación de la cuenta");
            }

        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo");
        }
    }

    public static ArrayList<String> getCredenciales() {
        return credenciales;
    }

    public static ArrayList<Entrenador> getListEntrenadores() {
        return listEntrenadores;
    }
}
