import Entidades.Alumno;
import Servicios.AlumnosDB;
import Servicios.CursoDB;
import Servicios.ProfesorDB;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

import java.util.Scanner;

public class Main {
    public static void main (String [] args) {
        Scanner entrada = new Scanner(System.in);
        ObjectContainer db = Db4oEmbedded.openFile("alumnos.db4o");
        AlumnosDB alumnosDB = new AlumnosDB();
        CursoDB cursoDB = new CursoDB();
        ProfesorDB profesorDB = new ProfesorDB();

        System.out.println("1. Agregar Alumno");
        System.out.println("2. Agregar Curso");
        System.out.println("3. Agregar Profesor");
        System.out.println("4. Agregar Alumno a Curso");
        System.out.println("5. Agregar Alumno a Curso");
        System.out.println("6. Consultar Curso");
        System.out.println("7. Consultar Alumno de un Profesor");
        System.out.println("8. Consultar Alumno de un Curso");
        System.out.println("9. Mostrar la especialidad de un Profesor");
        System.out.println("10. Eliminar Curso");
        System.out.println("11. Buscar Profesor por Especialiadad");
        System.out.println("12. Salir");
        try {
            int opcionUsuario = entrada.nextInt();
            switch (opcionUsuario) {
                case 1: {
                    System.out.println("Nombre");
                        String nombreAlumno = entrada.nextLine();
                    System.out.println("Edad");
                        int edadAlumno = entrada.nextInt();
                    System.out.println("Curso");
                        String curso = entrada.nextLine();
                        if (cursoDB.consultarCurso(curso, db)) {
                            Alumno alumno = new Alumno(nombreAlumno, edadAlumno, null );
                            db.store(alumno);
                        } else {
                            Alumno alumno = new Alumno(nombreAlumno, edadAlumno,null );
                            db.store(alumno);
                        }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
