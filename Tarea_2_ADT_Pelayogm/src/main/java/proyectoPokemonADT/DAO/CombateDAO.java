package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntidad;

import java.util.List;

public interface CombateDAO {
    void crearCombate (CombateEntidad combate);
    List<CombateEntidad> obtenerTodosLosCombates();
    CombateEntidad obtenerCombatePorId(int id);
    void actualiarCombate (CombateEntidad combate);
    void eliminarCombate (int id);
}
