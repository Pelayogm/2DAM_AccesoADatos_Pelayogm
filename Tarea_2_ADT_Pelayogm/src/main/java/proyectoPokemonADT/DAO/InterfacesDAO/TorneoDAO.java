package proyectoPokemonADT.DAO.InterfacesDAO;

import proyectoPokemonADT.Entidades.TorneoEntidad;

import java.util.List;

public interface TorneoDAO {
    void crearTorneo (TorneoEntidad torneo);
    List<TorneoEntidad> obtenerTodosLosTorneos();
    TorneoEntidad obtenerTorneoPorId (int id);
    void actualizarTorneo (TorneoEntidad torneo);
    void eliminarTorneo (int id);
}
