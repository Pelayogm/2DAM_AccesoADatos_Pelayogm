package proyectoPokemonADT.DAO.InterfacesDAO;
import proyectoPokemonADT.Entidades.*;

import java.util.List;

public interface EntrenadorDAO {
    void crearEntrenador (EntrenadorEntidad entrenador);
    List<EntrenadorEntidad> obtenerTodosLosEntrenadores();
    EntrenadorEntidad obtenerEntrenadorPorId(int id);
    void actualizarEntrenador (EntrenadorEntidad entrenador);
    void eliminarEntrenador (int id);
}
