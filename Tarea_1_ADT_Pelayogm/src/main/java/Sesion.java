import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Sesion {
    public static ArrayList<Usuario> listEntrenadores = new ArrayList<>();
    private static ArrayList<Torneo> listTorneos = Funciones.getListTorneos();
    private static ArrayList<String> listCredenciales = Credenciales.getCredenciales();

    //BOOLEANO PARA COMPROBAR QUE SE HAN COMPROBADO LOS TORNEOS
    private static boolean flag = false;

    //SI EXISTE AL MENOS UN TORNEO FLAG ES TRUE
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
                if (Credenciales.comprobarCredenciales(datosIntroducidosUsuario)) {
                    for (int i = 0; i < listCredenciales.size(); i++) {
                        if (datosIntroducidosUsuario.equals(listCredenciales.get(i)) && listCredenciales.get(i + 1).equals("Administrador")) {
                            Admin admin = new Admin(1);
                            Funciones.MostrarFunciones(admin);
                            return admin;
                        }

                        if (datosIntroducidosUsuario.equals(listCredenciales.get(i)) && listCredenciales.get(i + 1).equals("AdministradorTorneos")) {
                            AdminTorneos adminTorneos = new AdminTorneos();
                            Funciones.MostrarFunciones(adminTorneos);
                            return adminTorneos;
                        }

                        if (datosIntroducidosUsuario.equals(listCredenciales.get(i)) && listCredenciales.get(i + 1).equals("Entrenador")); {
                          Entrenador entrenador;
                          File datos_dat = new File(".", "Usuarios.dat");
                          try {
                              GestorArchivosDat.leerEntrenadoresDat(datos_dat);
                              for (int x = 0; i < listEntrenadores.size(); x++) {
                                  if (listEntrenadores.get(x).getNombre().equals(nombreEntrenador)) {
                                      entrenador = (Entrenador) listEntrenadores.get(x);
                                      System.out.println("Sesion iniciada");
                                      Funciones.MostrarFunciones(entrenador);
                                      return entrenador;
                                  }
                              }
                          } catch (Exception e) {

                          }
                        }


                    }
                } else {
                    System.out.println("No se han encontrado sus credenciales");
                    try {
                        System.out.println("¿Desea crear una cuenta?");
                        Scanner scanner_2 = new Scanner(System.in);
                        System.out.println("1. Sí quiero crear una cuenta | 2. No deseo crear una cuenta");
                        int numeroScanner = scanner_2.nextInt();
                        if (numeroScanner == 1) {
                            CrearCuenta();
                        } else {
                            System.out.println("Entendido no se creará una cuenta entonces.");
                        }
                    } catch (Exception e) {
                        System.out.println("Valor introducido no valido");

                    }
                }
            } catch (Exception e) {
                System.out.println("Error en la busqueda de datos");
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

            //SE PREPARA UN ARCHIVO PARA ESCRIBIR EL ENTRENDADOR EN UN ARCHIVO DAT Y UNO PARA LEER LOS TORNEOS
            File file_torneos = new File(".", "Torneos.dat");
            File file_escribirdatos;
            GestorArchivosDat.cargarTorneo(file_torneos);
            ComprobacionTorneos();

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File(".", "Credenciales.txt");
            Credenciales.leerFichero(file);
            Entrenador entrenador = new Entrenador();

            if (flag) {
                try {
                    file_escribirdatos = new File(".", "Usuarios.dat");
                    long idUsuario = Credenciales.getContadorLineas() / 3;
                    //SE LLAMA A CREAR ENTRENADOR
                    entrenador = Entrenador.crearEntrenador(nombreUsuario, idUsuario);
                    //CON LO QUE RETORNA CREAR ENTRENADOR ESCRIBIMOS EL DAT
                    GestorArchivosDat.escribirEntrenadoresDat(file_escribirdatos, entrenador);
                } catch (Exception e) {
                    System.out.println("Error con el DAT");
                }
            } else {
                System.out.println("No esta disponible este servicio por el momento");
                System.out.println("Informese de si existe algún torneo para registrarse");
            }

            //COMPROBAR EL ARRAYLIST DE CREDENCIALES PARA VER SI HAY COINCIDENCIAS
            try {
                if (Credenciales.comprobarCredenciales(datosIntroducidosUsuario)) {
                    Scanner scanner_2 = new Scanner(System.in);
                    System.out.println("El usuario y contraseña introducido ya existe");
                    System.out.println("¿Desea iniciar sesión? - 1. Si | 2. No");
                    try {
                        int opcionUsuario = scanner_2.nextInt();
                        switch (opcionUsuario) {
                            case 1 -> {
                                Sesion.IniciarSesion();
                            }

                            case 2 -> {
                                System.out.println("Adios");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Valor no válido");
                    }

                } else {
                    //SI LA LISTA DE TORNEOS TIENE DATOS
                    if (flag) {
                        //SE AÑADE AL ARCHIVO DE CREDENCIALES LOS DATOS DEL USUARIO
                        String rolUsuario = "Entrenador";
                        long idUsuario = Credenciales.getContadorLineas() / 3;
                        String idUsuarioString = Long.toString(idUsuario);
                        Credenciales.escribirFichero(file, datosIntroducidosUsuario, rolUsuario, idUsuarioString);
                        System.out.println("Cuenta creada con éxito");
                        Funciones.MostrarFunciones(entrenador);
                    }
                }
            } catch (Exception e) {
                System.out.println("No se ha podido comprobar las credenciales");
            }
        } catch (Exception e) {
            System.out.println("No se ha podido encontrar el fichero de credenciales");
        }
    }

    public static ArrayList<Usuario> getListEntrenadores() {
        return listEntrenadores;
    }
}
