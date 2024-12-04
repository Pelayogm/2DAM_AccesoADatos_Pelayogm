package proyectoPokemonADT.DAO.InterfacesDAO;

import proyectoPokemonADT.Entidades.TorneoAdminEntidad;

import java.util.List;

public interface TorneoAdminDAO {
    void crearTorneoAdmin(TorneoAdminEntidad torneoAdmin);
    List<TorneoAdminEntidad> obtenerTodosAdminTorneo();
    int obtenerAdminTorneoPorId(int id);
    List<TorneoAdminEntidad> obtenerTorneosPorAdminId(int id);
    void eliminarTorneoAdmin(int id);
}
