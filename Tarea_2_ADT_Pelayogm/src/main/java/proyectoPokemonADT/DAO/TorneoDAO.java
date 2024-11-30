package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.TorneoEntidad;

import java.util.List;

public interface TorneoDAO {
    void crearTorneo (TorneoEntidad torneo);
    List<TorneoEntidad> obtenerTodosLosTorneos();
    TorneoEntidad obtenerTorneoPorId (int id);
    void actualizarTorneo (TorneoEntidad torneo);
    void eliminarTorno (int id);
}
