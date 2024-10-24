import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Sesion {
    private static ArrayList<String> credenciales = new ArrayList<>();
    public static ArrayList<Entrenador> listEntrenadores = new ArrayList<>();
    private static boolean sesion = false;

    public static boolean isSesion() {
        return sesion;
    }

    public static void setSesion(boolean sesion) {
        Sesion.sesion = sesion;
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
            leerFichero(file);

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
                                    leerEntrenadoresDat(file_entrenadores);
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
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("¿Usuario?");
            String nombreUsuario = scanner.nextLine();
            stringBuilder.append(nombreUsuario);
            stringBuilder.append("-");
            System.out.println("¿Contraseña?");
            stringBuilder.append(scanner.nextLine());
            String datosIntroducidosUsuario = stringBuilder.toString();
            System.out.println("A continuacion va a se va a crear una cuenta en el club de Entrenadores Pokemon");

            File file_escribirdatos;
            try {
                file_escribirdatos = new File(".", "Entrenadores.dat");
                Entrenador entrenador = Entrenador.crearEntrenador(nombreUsuario);
                escribirEntrenadoresDat(file_escribirdatos, entrenador);
            } catch (Exception e) {
                System.out.println("Error con el DAT");
            }

            //CREAMOS EL ARCHIVO FILE PARA MANDARSELO AL METODO DE LEER
            file = new File(".", "Credenciales.txt");
            //RELLENAR EL ARRAYLIST CON LOS DATOS QUE HAY EN EL CREDENCIALES.TXT
            leerFichero(file);

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
                if (!usuarioExiste) {
                    escribirFichero(file, datosIntroducidosUsuario);
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
                System.out.println("Error en el crear cuenta al final");
            }

        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo");
        }

    }

    private static void leerFichero(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String linea = bufferedReader.readLine();
                while (linea != null) {
                    credenciales.add(linea);
                    linea = bufferedReader.readLine();
                }
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                System.out.println("Fin del fichero");
            }

        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo para leer");
        }
    }

    private static void escribirFichero (File file, String datosParaEscribir) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            try {
                fileWriter.write(datosParaEscribir + "\n");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("No se ha podido escribir");
            }
        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo para escribir");
        }
    }

    private static void escribirEntrenadoresDat (File file, Entrenador entrenador) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file, true);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
                        objectOutputStream.writeObject(entrenador);
                    objectOutputStream.close();
                bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("Error en la escritura");
        }
    }

    private static void leerEntrenadoresDat (File file) {
        FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
                            try {
                                while (true) {
                                    listEntrenadores.add((Entrenador) objectInputStream.readObject());
                                }
                            } catch (Exception e) {

                            }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

}
