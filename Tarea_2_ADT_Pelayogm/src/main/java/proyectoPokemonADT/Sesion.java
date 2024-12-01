package proyectoPokemonADT;

import proyectoPokemonADT.Administradores.Admin;
import proyectoPokemonADT.Administradores.AdminTorneos;
import proyectoPokemonADT.Credenciales.Credenciales;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
            System.out.println("¿Usuario?");
            String nombreEntrenador = scanner.nextLine();
                if (nombreEntrenador.isEmpty()) {
                    System.out.println("No se permiten campos vacíos");
                    IniciarSesion();
                }
            System.out.println("¿Contraseña?");
                String contrasena = scanner.nextLine();
                if (contrasena.isEmpty()) {
                    System.out.println("No se permiten campos vacíos");
                    IniciarSesion();
                }

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Credenciales.txt");

            //RELLENAR EL ARRAYLIST CON LOS DATOS QUE HAY EN EL CREDENCIALES.TXT
            Credenciales.leerFichero(file);

            //COMPROBAR EL ARRAYLIST DE CREDENCIALES PARA VER SI HAY COINCIDENCIAS
            try {
                if (Credenciales.comprobarCredenciales(nombreEntrenador, contrasena)) {
                    for (int i = 0; i < listCredenciales.size(); i++) {
                        if (nombreEntrenador.equals(listCredenciales.get(i)) && contrasena.equals(listCredenciales.get(i + 1)) && listCredenciales.get(i + 2).equals("Administrador")) {
                            Admin admin = new Admin(1);
                            Funciones.MostrarFunciones(admin);
                            return admin;
                        }

                        if (nombreEntrenador.equals(listCredenciales.get(i)) && contrasena.equals(listCredenciales.get(i + 1)) && listCredenciales.get(i + 2).equals("AdministradorTorneos")) {
                            AdminTorneos adminTorneos = new AdminTorneos();
                            Funciones.MostrarFunciones(adminTorneos);
                            return adminTorneos;
                        }

                        if (nombreEntrenador.equals(listCredenciales.get(i)) && contrasena.equals(listCredenciales.get(i + 1)) && listCredenciales.get(i + 2).equals("Entrenador")) {
                          Entrenador entrenador;
                          File datos_dat = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Usuarios.dat");
                          try {
                              GestorArchivosDat.leerEntrenadoresDat(datos_dat);
                              for (int x = 0; x < listEntrenadores.size(); x++) {
                                  if (listEntrenadores.get(x).getNombre().equals(nombreEntrenador)) {
                                      entrenador = (Entrenador) listEntrenadores.get(x);
                                      System.out.println("Sesion iniciada");
                                      Funciones.MostrarFunciones(entrenador);
                                      return entrenador;
                                  }
                              }
                          } catch (Exception e) {
                              System.out.println("Problemas aqui");
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
            System.out.println("¿Usuario?");
            String nombreUsuario = scanner.nextLine();
            System.out.println("¿Contraseña?");
            String constrasenaUsuario = scanner.nextLine();
            System.out.println("A continuacion va a se va a crear una cuenta en el club de Entrenadores Pokemon");

            //SE PREPARA UN ARCHIVO PARA ESCRIBIR EL ENTRENDADOR EN UN ARCHIVO DAT Y UNO PARA LEER LOS TORNEOS
            File file_torneos = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Torneos.dat");
            File file_escribirdatos;
            GestorArchivosDat.cargarTorneo(file_torneos);
            ComprobacionTorneos();

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Credenciales.txt");
            Credenciales.leerFichero(file);
            Entrenador entrenador = new Entrenador();

            if (flag) {
                try {
                    file_escribirdatos = new File("src/main/java/proyectoPokemonADT/ArchivosDelPrograma", "Usuarios.dat");
                    int ultimoId = Integer.parseInt(listCredenciales.get(listCredenciales.size() - 1));
                    long idUsuario = ultimoId + 1;
                    //SE LLAMA A CREAR ENTRENADOR
                    entrenador = Entrenador.crearEntrenador(constrasenaUsuario, idUsuario);
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
                if (Credenciales.comprobarCredenciales(nombreUsuario, constrasenaUsuario)) {
                    Scanner scanner_2 = new Scanner(System.in);
                    System.out.println("El usuario y contraseña introducido ya existe");
                    System.out.println("¿Desea iniciar sesión? - 1. Si | 2. No");
                    try {
                        int opcionUsuario = scanner_2.nextInt();
                        switch (opcionUsuario) {
                            case 1 -> Sesion.IniciarSesion();
                            case 2 -> System.out.println("Adios");
                        }
                    } catch (Exception e) {
                        System.out.println("Valor no válido");
                    }

                } else {
                    //SI LA LISTA DE TORNEOS TIENE DATOS
                    if (flag) {
                        //SE AÑADE AL ARCHIVO DE CREDENCIALES LOS DATOS DEL USUARIO
                        if (entrenador != null) {
                            String rolUsuario = "Entrenador";
                            int ultimoId = Integer.parseInt(listCredenciales.get(listCredenciales.size() - 1));
                            long idUsuario = ultimoId + 1;
                            String idUsuarioString = Long.toString(idUsuario);
                            Credenciales.escribirFichero(file, nombreUsuario, constrasenaUsuario, rolUsuario, idUsuarioString);
                            System.out.println("Cuenta creada con éxito");
                            Funciones.MostrarFunciones(entrenador);
                        } else {
                            Sesion.CrearCuenta();
                        }
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
