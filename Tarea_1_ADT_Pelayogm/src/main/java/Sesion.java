import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Sesion {
    private static ArrayList<String> credenciales = new ArrayList<>();
    private static boolean sesion = false;

    public static boolean isSesion() {
        return sesion;
    }

    public static void setSesion(boolean sesion) {
        Sesion.sesion = sesion;
    }

    public static boolean IniciarSesion () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sistema de la Calle Victoria");
        File file;
        boolean usuarioExiste = false;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("¿Usuario?");
                stringBuilder.append(scanner.nextLine());
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
                            System.out.println("SESION INICIADA");
                            sesion = true;
                            usuarioExiste = true;
                            return true;
                        }
                    }

                    try {
                        if (!usuarioExiste) {
                            System.out.println("¿Desea crear una cuenta?");
                            Scanner scanner_2 = new Scanner(System.in);
                            System.out.println("1. Sí quiero crear una cuenta | 2. No deseo crear una cuenta");
                            int numeroScanner = scanner.nextInt();
                            if (numeroScanner == 1) {
                                CrearCuenta();
                            } else {
                                System.out.println("Entendido no se creará una cuenta entonces.");
                                return true;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Valor fuera de los rangos");
                    }
                } catch (Exception e) {
                    System.out.println("HA HABIDO UN ERROR EN LA COTEJACION DE DATOS");
                }

        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivo");
        }
        return false;
    }

    public static void CrearCuenta () {
        System.out.println("Registrarse en la Calle Victoria");
        Scanner scanner = new Scanner(System.in);
        File file;
        boolean usuarioExiste = false;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            System.out.println("¿Usuario?");
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append("-");
            System.out.println("¿Contraseña?");
            stringBuilder.append(scanner.nextLine());
            String datosIntroducidosUsuario = stringBuilder.toString();

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
                        break;
                    }
                }
                if (!usuarioExiste) {
                    escribirFichero(file, datosIntroducidosUsuario);
                    System.out.println("Cuenta creada con éxito");
                }


            } catch (Exception e) {
                System.out.println("HA HABIDO UN ERROR EN LA COTEJACION DE DATOS");
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

}
