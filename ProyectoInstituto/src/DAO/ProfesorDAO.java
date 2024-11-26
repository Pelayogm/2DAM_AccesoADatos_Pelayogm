package DAO;

import entidades.ProfesorEntidad;

import java.util.List;

public interface ProfesorDAO {
    public void crearProfesor (ProfesorEntidad profesor);
    public ProfesorEntidad obtenerProfesorPorId (String id);
    List<ProfesorEntidad> obtenerTodosLosProfesores ();
    public void actualizarProfesor (ProfesorEntidad profesor);
    public void eliminarProfesor (String id);
}
