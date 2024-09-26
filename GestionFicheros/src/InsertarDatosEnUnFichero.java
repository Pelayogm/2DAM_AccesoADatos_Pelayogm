import java.io.*;

public class InsertarDatosEnUnFichero {

    public static void main (String [] args) {
        File file = new File(".", "Personas.dat");
        try {
            rellenarDatos(file);
            try {
                leerDatos(file);
            } catch (Exception e) {
                System.out.println("Final");
            }
        } catch (Exception e) {
            System.out.println("Ha habido un problema con la escritura de los nombres");
        }
    }

    public static void rellenarDatos (File file) {
        String [] nombres = {"Ana", "Carlos", "Lucía", "Miguel", "Sofía"};
        int [] edades = {25, 30, 22, 28, 26};

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                    for (int i = 0; i < nombres.length; i++) {
                        dataOutputStream.writeUTF(nombres[i] + ", ");
                        dataOutputStream.writeInt(edades[i]);
                    }
                dataOutputStream.close();

        } catch (Exception e) {
            System.out.println("Ha habido un error en escritura");
        }
    }

    public static void leerDatos (File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                try {
                    while (true) {
                        System.out.println("Nombre: " + dataInputStream.readUTF() + " Edad: " + dataInputStream.readInt() + ".");
                    }
                } catch (EOFException e) {
                    System.out.println("Fin del archivo");
                }
        } catch (Exception e) {
            System.out.println("Ha habido un error");
        }
    }
}
