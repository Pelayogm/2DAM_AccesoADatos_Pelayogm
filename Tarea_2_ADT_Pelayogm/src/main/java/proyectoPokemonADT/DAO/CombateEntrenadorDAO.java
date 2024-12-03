package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntrenadorEntidad;

import java.util.List;

public interface CombateEntrenadorDAO {
    void crearCombateTorneo (CombateEntrenadorEntidad combateEntrenador);
    void eliminarCombateTorneo(int id);
    List<CombateEntrenadorEntidad> obtenerTodosLosParticipantes();
    void actualizarCombateEntrenador (int idCombate, CombateEntrenadorEntidad combateEntrenador);
    List<Integer> obtenerParticipantesPorIdDelCombate(int idCombate);
}
