package Servicios;

import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Profesor;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import java.util.List;

public class ProfesorDB {
    public void insertarProfesor(Profesor profesor, ObjectContainer db) {
        db.store(profesor);
    }

    public void mostrarTodosLosAlumnos(ObjectContainer db) {
        Query query = db.query();
        query.constrain(Alumno.class);
        List<Profesor> profesoresDisponibles = query.execute();
        if (!profesoresDisponibles.isEmpty()) {
            for (int i = 0; i < profesoresDisponibles.size(); i++) {
                System.out.println(profesoresDisponibles.get(i).getNombre());
            }
        }
    }

    public void consultarEspecialidad(String nombreProfesor, ObjectContainer db) {
        Query query = db.query();
        Profesor profesor = new Profesor(nombreProfesor, null, null);
        query.constrain(profesor);
        List<Profesor> profesoresDisponibles = query.execute();
        if (!profesoresDisponibles.isEmpty()) {
            for (int i = 0; i < profesoresDisponibles.size(); i++) {
                System.out.println(profesoresDisponibles.get(i).getNombre() + " | " + profesoresDisponibles.get(i).getEspecialidad());
            }
        } else {
            System.out.println("No existe ese profesor especificado");
        }
    }

    public void asignarCurso(Curso curso, Profesor profesor, ObjectContainer db) {
        Query query = db.query();
        query.constrain(profesor);
        List<Profesor> profesores = query.execute();
        Profesor profesorActualizar = profesores.get(0);
        List<Curso> cursos = profesorActualizar.getCursosProfesor();
        cursos.add(curso);
        profesorActualizar.setCursosProfesor(cursos);
        db.store(profesorActualizar);
    }

    public void consultarProfesorPorEspecialidad(String especialidadProfesor, ObjectContainer db) {
        Query query = db.query();
        Profesor profesor = new Profesor(null, especialidadProfesor, null);
        query.constrain(profesor);
        List<Profesor> profesoresDisponibles = query.execute();
        if (!profesoresDisponibles.isEmpty()) {
            for (int i = 0; i < profesoresDisponibles.size(); i++) {
                System.out.println(profesoresDisponibles.get(i).getNombre() + " | " + profesoresDisponibles.get(i).getEspecialidad());
            }
        } else {
            System.out.println("No existe ese profesor especificado");
        }
    }

    public void actualizarEspecialidad(String nombreProfesor, String nuevaEspecialidad,ObjectContainer db) {
        Query query = db.query();
        Profesor profesor = new Profesor(nombreProfesor, null, null);
        query.constrain(profesor);
        List<Profesor> profesoresDisponibles = query.execute();
        if (!profesoresDisponibles.isEmpty()) {
            for (int i = 0; i < profesoresDisponibles.size(); i++) {
                System.out.println(profesoresDisponibles.get(i).getNombre() + " | " + profesoresDisponibles.get(i).getEspecialidad());
                profesoresDisponibles.get(i).setEspecialidad(nuevaEspecialidad);
                db.store(profesoresDisponibles.get(i));
            }
        } else {
            System.out.println("No existe ese profesor especificado");
        }
    }


}
