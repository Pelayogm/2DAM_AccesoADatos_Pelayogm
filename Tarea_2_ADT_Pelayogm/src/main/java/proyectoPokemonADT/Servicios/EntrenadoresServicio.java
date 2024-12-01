package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.DAO.EntrenadorDAOImplementacion;
import proyectoPokemonADT.DTO.EntrenadorDTO;
import proyectoPokemonADT.Entidades.EntrenadorEntidad;
import proyectoPokemonADT.Entrenador;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class EntrenadoresServicio {

    private static EntrenadoresServicio instancia;
    private static EntrenadorDAOImplementacion entrenadorDAOImplementacion;
    private static DataSource dataSource;

    private EntrenadoresServicio (DataSource dataSource) {
        entrenadorDAOImplementacion = EntrenadorDAOImplementacion.getInstancia(dataSource);
    }

    public static EntrenadoresServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new EntrenadoresServicio(dataSource);
        }
        return instancia;
    }

    public void crearEntrenador (EntrenadorDTO entrenador) {
        EntrenadorEntidad entrenadorEntidad = new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
        entrenadorDAOImplementacion.crearEntrenador(entrenadorEntidad);
    }

    public EntrenadorDTO obtenerEntrenadorPorId (int id) {
        EntrenadorEntidad entrenadorEntidad = entrenadorDAOImplementacion.obtenerEntrenadorPorId(id);
        EntrenadorDTO entrenadorDTO = new EntrenadorDTO(entrenadorEntidad.getIdEntrenador(), entrenadorEntidad.getNombreEntrenador(), entrenadorEntidad.getNacionalidadEntrenador());
        return entrenadorDTO;
    }

    public List<EntrenadorDTO> obtenerTodosLosEntrenador () {
        List<EntrenadorEntidad> listaDeEntrenadoresEntidad = entrenadorDAOImplementacion.obtenerTodosLosEntrenadores();
        List<EntrenadorDTO> listaDeEntrenadoresDto = new ArrayList<>();

        for (int i = 0; i < listaDeEntrenadoresEntidad.size(); i++) {
            EntrenadorEntidad entrenadorEntidad = listaDeEntrenadoresEntidad.get(i);
            EntrenadorDTO entrenadorDTO = new EntrenadorDTO(entrenadorEntidad.getIdEntrenador(), entrenadorEntidad.getNombreEntrenador(), entrenadorEntidad.getNacionalidadEntrenador());
            listaDeEntrenadoresDto.add(entrenadorDTO);
        }
        return listaDeEntrenadoresDto;
    }

    public boolean eliminarEntrenador (int id) {
        try {
            entrenadorDAOImplementacion.eliminarEntrenador(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public EntrenadorDTO actualizarEntrenador (int id, EntrenadorDTO entrenador) {
        EntrenadorEntidad entrenadorEntidad = new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
        entrenadorDAOImplementacion.actualizarEntrenador(entrenadorEntidad);
        EntrenadorEntidad entrenadorEntidadActualizado = entrenadorDAOImplementacion.obtenerEntrenadorPorId(id);
        EntrenadorDTO entrenadorDTO = new EntrenadorDTO(entrenadorEntidadActualizado.getIdEntrenador(), entrenadorEntidadActualizado.getNombreEntrenador(), entrenadorEntidadActualizado.getNacionalidadEntrenador());
        return entrenadorDTO;
    }

    private EntrenadorEntidad mapearDtoAEntidad (EntrenadorDTO entrenador) {
        return new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
    }

    private EntrenadorDTO mapearEntidadADto (EntrenadorEntidad entrenador) {
        return new EntrenadorDTO(entrenador.getIdEntrenador(), entrenador.getNombreEntrenador(), entrenador.getNacionalidadEntrenador());
    }
}
