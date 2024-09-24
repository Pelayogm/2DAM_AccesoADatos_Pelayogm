import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("FicheroMaster");
    }

    private static void verDir() {
        String dir = ".";
        String [] archivos;
        File fichero = new File(dir);
        File fichero2;
        archivos = fichero.list();

        int longitudArchivos = archivos.length;
            for (int i = 0; i < longitudArchivos; i++) {
                fichero2 = new File(fichero, archivos[i]);

                if (fichero2.isDirectory()) {
                    System.out.println("Carpeton");
                } else {
                    System.out.println("Archivito");
                }
            }
    }

    public static void crearCarpeta (String destino) {
        File carpeta = new File(destino, "carpeta");
            carpeta.mkdir();
    }

    public static void crearFichero (String destino, String nombre) throws IOException {
        File f = new File(destino, nombre);
        f.createNewFile();
    }

    private static void cambiaFicheros () {
        File carp = new File("Ficheros");
        File fich = new File(carp,"fichero1.txt");
        File fich2 = new File(carp,"fichero2.txt");
        carp.mkdir();
        try {
            if (fich.createNewFile()) {
                System.out.println("Fichero " + fich.getName() + " creado correctamente");
            } else {            System.out.println("El fichero no se puedo crear");
            }
            if (fich2.createNewFile()) {
                System.out.println("Fichero " + fich2.getName() +" creado correctamente");
            } else {            System.out.println("El fichero no se puedo crear");
            }    } catch (IOException e) {        e.printStackTrace();
        }    File newfile = new File(carp, "nuevonombre.txt");
        fich2.renameTo(newfile);
        System.out.println("El nuevo fichero es: " + fich2.getName());
    }


}