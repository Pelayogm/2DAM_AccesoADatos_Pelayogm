import java.io.*;

public class EscribirArchivo_Binario {

    public static void main (String [] args) {
        File file = new File(".", "Archivo.dat");
        try {
            rellenarArchivo(file);
            try {
                int i;
                FileInputStream fileInputStream = new FileInputStream(file);
                while ((i = fileInputStream.read()) != -1) {
                    System.out.print((byte) i + ", ");
                }
                fileInputStream.close();

            } catch (Exception e) {
                System.out.println("No se ha podido leer el archivo");
            }
        } catch (Exception e) {
            System.out.println("Ha habido un error lamentamos las molestias");
        }
    }

    public static void rellenarArchivo(File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
       try {
           for (int i = 0; i < 100; i++) {
               String s = String.valueOf(i);
               fileOutputStream.write(i);
           }
           fileOutputStream.close();
       } catch (Exception e) {
           System.out.println("No se ha podido completar la operación");
       }
    }


}
