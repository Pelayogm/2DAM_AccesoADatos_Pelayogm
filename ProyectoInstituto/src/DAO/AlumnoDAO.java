package DAO;

import entidades.AlumnoEntidad;

import java.util.List;

public interface AlumnoDAO {
    public void crearAlumno (AlumnoEntidad alumno);
    public AlumnoEntidad obtenerAlumnoPorId (String id);
    public List<AlumnoEntidad> obtenerTodosLosAlumnos ();
    public void actualizarAlumno(AlumnoEntidad alumno);
    public void eliminarAlumno(String id);
}
