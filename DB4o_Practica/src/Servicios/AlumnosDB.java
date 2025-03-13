package Servicios;
import Entidades.Alumno;
import Entidades.Curso;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import java.util.List;

public class AlumnosDB {

    public void insertarAlumno(Alumno alumno, ObjectContainer db) {
        db.store(alumno);
    }

    public void mostrarTodosLosAlumnos(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Alumno.class);
        List<Alumno> alumnosDisponibles = query.execute();
        if (!alumnosDisponibles.isEmpty()) {
            for (int i = 0; i < alumnosDisponibles.size(); i++) {
                System.out.println(alumnosDisponibles.get(i).getNombre());
            }
        }
    }

    public void eliminarAlumnoPorNombre(String nombreAlumno, ObjectContainer db) {
        Query query = db.query();
        Alumno alumno = new Alumno(nombreAlumno, 0, null);
        query.constrain(alumno);
        List<Alumno> alumnosAEliminar = query.execute();
        if (!alumnosAEliminar.isEmpty()) {
            for (int i = 0; i < alumnosAEliminar.size(); i++) {
                db.delete(alumnosAEliminar.get(i));
            }
        } else {
            System.out.println("No hay alumnos para eliminar");
        }
    }

    public void asignarCurso(Curso curso, Alumno alumno, ObjectContainer db) {
        Query query = db.query();
        query.constrain(alumno);
        List<Alumno> alumnos = query.execute();
        Alumno alumnoActualizar = alumnos.get(0);
        List<Curso> cursosAlumnos = alumnoActualizar.getCursosAlumnos();
        cursosAlumnos.add(curso);
        alumnoActualizar.setCursosAlumnos(cursosAlumnos);
        db.store(alumnoActualizar);
    }

}
