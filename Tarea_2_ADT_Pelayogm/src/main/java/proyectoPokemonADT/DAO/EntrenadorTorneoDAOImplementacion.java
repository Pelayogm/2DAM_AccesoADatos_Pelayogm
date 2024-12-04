package proyectoPokemonADT.DAO;

import proyectoPokemonADT.DAO.InterfacesDAO.EntrenadorTorneoDAO;
import proyectoPokemonADT.Entidades.EntrenadorTorneoEntidad;

import java.util.List;

public class EntrenadorTorneoDAOImplementacion implements EntrenadorTorneoDAO {
    @Override
    public void crearEntrenadorTorneo(EntrenadorTorneoEntidad entrenadorTorneo) {

    }

    @Override
    public void eliminarEntrenadorTorneo(int id) {

    }

    @Override
    public List<EntrenadorTorneoEntidad> obtenerTorneosDeUnEntrenador(int idUsuario) {
        return List.of();
    }
}
