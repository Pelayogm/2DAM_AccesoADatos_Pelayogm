package Servicios;

import Entidades.Curso;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import java.util.List;

public class CursoDB {
    public void insertarCurso(Curso curso, ObjectContainer db) {
        db.store(curso);
    }

    public void mostrarTodosLosCursos(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Curso.class);
        List<Curso> cursosDisponibles = query.execute();
        if (!cursosDisponibles.isEmpty()) {
            for (int i = 0; i < cursosDisponibles.size(); i++) {
                System.out.println(cursosDisponibles.get(i).getNombreCurso());
            }
        }
    }

    public boolean consultarCurso(String nombreCurso, ObjectContainer db) {
        Query query = db.query();
        Curso curso = new Curso(null, nombreCurso, null);
        query.constrain(curso);
        List<Curso> listaCursos = query.execute();
        if (!listaCursos.isEmpty()) {
            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println(listaCursos.get(i).toString());

            }
            return true;
        } else {
            System.out.println("No existe el curso solicitado");
            return false;
        }
    }

    public void consultarAlumnos(String nombreCurso, ObjectContainer db) {
        Query query = db.query();
        Curso curso = new Curso(null, nombreCurso, null);
        query.constrain(curso);
        List<Curso> listaCursos = query.execute();
        if (!listaCursos.isEmpty()) {
            for (int i = 0; i < listaCursos.size(); i++) {
                Curso cursoActual = listaCursos.get(i);
                for (int x = 0; i < cursoActual.listaAlumnos.size(); x++) {
                    System.out.println(cursoActual.listaAlumnos.get(x).toString());
                }
            }
        }
    }

    public void eliminarCursoPorNombre(String nombreCurso, ObjectContainer db) {
        Query query = db.query();
        Curso curso = new Curso(null, nombreCurso, null);
        query.constrain(curso);
        List<Curso> cursosAEliminar = query.execute();
        if (!cursosAEliminar.isEmpty()) {
            for (int i = 0; i < cursosAEliminar.size(); i++) {
                db.delete(cursosAEliminar.get(i));
            }
        } else {
            System.out.println("No hay cursos para eliminar");
        }
    }


}
