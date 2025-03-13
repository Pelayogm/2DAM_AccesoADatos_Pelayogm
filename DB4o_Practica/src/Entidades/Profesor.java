package Entidades;

import java.util.List;

public class Profesor {
    public String nombre;
    public String especialidad;
    public List<Curso> cursosProfesor;

    public Profesor(String nombre, String especialidad, List<Curso> cursosProfesor) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.cursosProfesor = cursosProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Curso> getCursosProfesor() {
        return cursosProfesor;
    }

    public void setCursosProfesor(List<Curso> cursosProfesor) {
        this.cursosProfesor = cursosProfesor;
    }

    @Override
    public String toString() {
        return "Entidades.Profesor{" +
                "nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", cursosProfesor=" + cursosProfesor +
                '}';
    }
}
