package servicios;

import DAO.ModuloDAOImplementacion;
import DAO.ProfesorDAOImplementacion;
import DTO.ProfesorDTO;
import entidades.ProfesorEntidad;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class ProfesorServicio {

    private static ProfesorServicio instancia;
    private ProfesorDAOImplementacion profesorDAOImplementacion;
    private ModuloDAOImplementacion moduloDAOImplementacion;
    private DataSource dataSource;

    private ProfesorServicio (DataSource dataSource) {
        this.profesorDAOImplementacion = ProfesorDAOImplementacion.getInstacia(dataSource);
        this.moduloDAOImplementacion = ModuloDAOImplementacion.getInstancia(dataSource);
    }

    public static ProfesorServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new ProfesorServicio(dataSource);
        }
        return instancia;
    }

    public void crearProfesor (ProfesorDTO profesor) {
        ProfesorEntidad profesorEntidad = new ProfesorEntidad(profesor.getIdProfesor(), profesor.getNombreProfesor(), profesor.getCiudad());
        profesorDAOImplementacion.crearProfesor(profesorEntidad);
    }

    public ProfesorDTO obtenerProfesorPorId (String id) {
        List<String> modulosProfesor = moduloDAOImplementacion.modulosDelProfesorId(id);
        ProfesorEntidad profesorEntidad = profesorDAOImplementacion.obtenerProfesorPorId(id);
        return new ProfesorDTO(profesorEntidad.getCodProfesor(), profesorEntidad.getNombreProfesor(), profesorEntidad.getResidencia(), modulosProfesor);
    }

    public List<ProfesorDTO> obtenerTodosLosProfesores () {
        List<ProfesorEntidad> profesoresEntidad = profesorDAOImplementacion.obtenerTodosLosProfesores();
        List<ProfesorDTO> listaDeProfesorDTO = new ArrayList<>();

        for (int i = 0; i < profesoresEntidad.size(); i++) {
            ProfesorEntidad profesorEntidadActual = profesoresEntidad.get(i);
            List<String> listaDeModulosProfesor = moduloDAOImplementacion.modulosDelProfesorId(profesorEntidadActual.getCodProfesor());
            ProfesorDTO profesorDTO = new ProfesorDTO(profesorEntidadActual.getCodProfesor(), profesorEntidadActual.getNombreProfesor(), profesorEntidadActual.getResidencia(), listaDeModulosProfesor);
            listaDeProfesorDTO.add(profesorDTO);
        }
        return listaDeProfesorDTO;
    }

    public boolean eliminarProfesor (String id) {
      try {
          profesorDAOImplementacion.eliminarProfesor(id);
          return true;
      } catch (Exception e) {
          return false;
      }
    }

    public ProfesorDTO actualizarProfesor (String id, ProfesorDTO profesor) {
        ProfesorEntidad profesorEntidad = new ProfesorEntidad(id , profesor.getNombreProfesor(), profesor.getCiudad());
        profesorDAOImplementacion.actualizarProfesor(profesorEntidad);
        ProfesorEntidad profesorEntidadActualizado = profesorDAOImplementacion.obtenerProfesorPorId(id);
        List<String> listaDeModulosProfesor = moduloDAOImplementacion.modulosDelProfesorId(id);
        return new ProfesorDTO(profesorEntidadActualizado.getCodProfesor(), profesorEntidadActualizado.getNombreProfesor(), profesorEntidadActualizado.getResidencia(), listaDeModulosProfesor);
    }

    public ProfesorEntidad mapearDtoAEntidad (ProfesorDTO profesor) {
        return new ProfesorEntidad(profesor.getIdProfesor(), profesor.getNombreProfesor(), profesor.getCiudad());
    }

    public ProfesorDTO mapearEntidadADto (ProfesorEntidad profesor) {
        List<String> modulosDelProfesor = moduloDAOImplementacion.modulosDelProfesorId(profesor.getCodProfesor());
        return new ProfesorDTO(profesor.getCodProfesor(), profesor.getNombreProfesor(), profesor.getResidencia(), modulosDelProfesor);
    }
}
