package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntrenadorEntidad;

import java.util.List;

public interface CombateEntrenadorDAO {
    void crearCombateTorneo (CombateEntrenadorEntidad combateEntrenador);
    void eliminarCombateTorneo(int id);
    List<CombateEntrenadorEntidad> obtenerTodosLosParticipantes();
    CombateEntrenadorEntidad obtenerParticipantesPorId(int idCombate);
}
