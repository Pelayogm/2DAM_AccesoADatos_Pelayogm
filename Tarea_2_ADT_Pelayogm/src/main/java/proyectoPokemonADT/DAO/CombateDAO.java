package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntidad;

import java.util.List;

public interface CombateDAO {
    void crearCombate (CombateEntidad combate);
    List<CombateEntidad> obtenerTodosLosCombates();
    List<CombateEntidad> obtenerTodosLosCombatesDeUnTorneo(int idTorneo);
    CombateEntidad obtenerCombatePorId(int id);
    void actualizarCombate(CombateEntidad combate);
    void eliminarCombate (int id);

}
