package Entidades;

import java.util.List;

public class Curso {
    public Profesor profesorCurso;
    public String nombreCurso;
    public List<Alumno> listaAlumnos;

    public Curso(Profesor profesorCurso, String nombreCurso, List<Alumno> listaAlumnos) {
        this.profesorCurso = profesorCurso;
        this.nombreCurso = nombreCurso;
        this.listaAlumnos = listaAlumnos;
    }

    public Profesor getProfesorCurso() {
        return profesorCurso;
    }

    public void setProfesorCurso(Profesor profesorCurso) {
        this.profesorCurso = profesorCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public String toString() {
        return "Entidades.Curso{" +
                "profesorCurso=" + profesorCurso +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", listaAlumnos=" + listaAlumnos +
                '}';
    }
}
