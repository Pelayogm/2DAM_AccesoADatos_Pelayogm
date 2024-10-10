import java.io.*;
import java.util.Scanner;

public class RegistrosRandomAccessFile {
    static final int tan_reg = 36;
    public static void main (String [] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File(".", "registro.dat");
        escribir(file);
        escribirFicheroPosicion(file, 2);
        escribirFicheroPosicion(file, 3);
        //leerFichero(file);
        leerFicheroPosicion(file, 1);
    }

    public static void escribir (File file) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿ID?");
        int id = scanner.nextInt();
        System.out.println("¿Apellido?");
        String test = scanner.nextLine();
        String apellido = scanner.nextLine();
        System.out.println("¿Departamento?");
        int departamento = scanner.nextInt();
        System.out.println("¿Salario?");
        double salario = scanner.nextDouble();

            while (apellido.length() < 10) {
                apellido = apellido + " ";
            }

            StringBuffer stringBuffer = null;

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.writeInt(id);
            stringBuffer = new StringBuffer(apellido);
            stringBuffer.setLength(10);
            randomAccessFile.writeChars(stringBuffer.toString());

            randomAccessFile.writeInt(departamento);
            randomAccessFile.writeDouble(salario);

            randomAccessFile.close();
        } catch (Exception e) {
            System.out.print("No se ha encontrar el archivo");
        }
    }

    public static void escribirFicheroPosicion (File file, int posicion) {
        RandomAccessFile randomAccessFile;

        Scanner scanner = new Scanner(System.in);
        System.out.println("¿ID?");
        int id = scanner.nextInt();
        System.out.println("¿Apellido?");
        String test = scanner.nextLine();
        StringBuilder apellido = new StringBuilder(scanner.nextLine());
        System.out.println("¿Departamento?");
        int departamento = scanner.nextInt();
        System.out.println("¿Salario?");
        double salario = scanner.nextDouble();

        char [] chars = new char[10];

        while (apellido.length() < 10) {
            apellido.append(" ");
        }


        for (int i = 0; i < chars.length; i++) {
            chars[i] = apellido.charAt(i);
        }

        try {
            randomAccessFile = new RandomAccessFile(file, ("rw"));
            randomAccessFile.seek(posicion);
            randomAccessFile.writeInt(id);
            for (int i = 0; i < chars.length; i++) {
                randomAccessFile.writeChar(chars[i]);
            }
            randomAccessFile.writeInt(departamento);
            randomAccessFile.writeDouble(salario);

            randomAccessFile.close();

        } catch (Exception e) {
            System.out.print("No se ha podido ejecutar el randomAccessFile");
        }
    }

    public static void leerFichero (File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
            try {
                int i;
                while ((i = dataInputStream.read()) != -1) {
                    System.out.print(dataInputStream.readInt());
                    for (int s = 0; s < 10; s++) {
                        System.out.print(dataInputStream.readChar());
                    }
                    System.out.print(dataInputStream.readInt());
                    System.out.println(dataInputStream.readDouble());
                }
            } catch (Exception e) {
                System.out.println("Final");
            }
        } catch (Exception e) {
            System.out.println("Ha habido un problemaceo");
        }
    }

    public static void leerFicheroPosicion (File file, int posicion) {
        int id;
        int departamento;
        Double salario;
        char [] apellido = new char[10];

        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
                //randomAccessFile.seek(36 *(posicion - 1));

                while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
                    randomAccessFile.seek(posicion);
                    id = randomAccessFile.readInt();
                    for (int i = 0; i < 10; i++) {
                        apellido[i] = randomAccessFile.readChar();
                    }
                    departamento = randomAccessFile.readInt();
                    salario = randomAccessFile.readDouble();

                    System.out.println("ID: " + id + " Apellido: " + new String(apellido) + " Departamento: " + departamento + " Salario: " + salario);
                    posicion = posicion + tan_reg;
                }
        } catch (Exception e) {
            System.out.println("No se ha encontrado el archivaceo");
        }
    }
}
