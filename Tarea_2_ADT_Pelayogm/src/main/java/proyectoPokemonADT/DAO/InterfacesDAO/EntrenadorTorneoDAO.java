package proyectoPokemonADT.DAO.InterfacesDAO;

import proyectoPokemonADT.Entidades.EntrenadorTorneoEntidad;

import java.util.List;

public interface EntrenadorTorneoDAO {
    void crearEntrenadorTorneo(EntrenadorTorneoEntidad entrenadorTorneo);
    void eliminarEntrenadorTorneo(int id);
    List<Integer> obtenerTorneosDeUnEntrenador (int idUsuario);
    List<Integer> obtenerIdDeEntrenadorPorIdTorneo (int idTorneo);

}
