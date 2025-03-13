package Entidades;

import java.util.List;

public class Alumno {

    private String nombre;
    private int edad;
    private List<Curso> cursosAlumnos;

    public Alumno(String nombre, int edad, List<Curso> ciclo) {
        this.nombre = nombre;
        this.edad = edad;
        this.cursosAlumnos = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Curso> getCursosAlumnos() {
        return cursosAlumnos;
    }

    public void setCursosAlumnos(List<Curso> cursosAlumnos) {
        this.cursosAlumnos = cursosAlumnos;
    }

    @Override
    public String toString() {
        return "Entidades.Alumno{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", cursosAlumnos=" + cursosAlumnos +
                '}';
    }
}
