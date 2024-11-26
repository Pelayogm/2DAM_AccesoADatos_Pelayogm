package DAO;

import entidades.ModuloEntidad;

import java.util.List;

public interface ModuloDAO {
    void crearModulo (ModuloEntidad modulo);
    ModuloEntidad obtenerModuloPorId (String id);
    List<ModuloEntidad> obtenerTodosLosModulos();
    void actualizarModulo (ModuloEntidad moduloEntidad);
    void eliminarModulo (String id);
    List<String> modulosDelProfesorId (String id);
}
