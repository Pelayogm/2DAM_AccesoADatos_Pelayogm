package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntrenadorEntidad;

import javax.sql.DataSource;
import java.util.List;

public class CombateEntrenadorDAOImplementacion implements CombateEntrenadorDAO {

    private static CombateEntrenadorDAOImplementacion instancia;
    private DataSource dataSource;

    private CombateEntrenadorDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CombateEntrenadorDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CombateEntrenadorDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public void crearCombateTorneo(CombateEntrenadorEntidad combateEntrenador) {

    }

    @Override
    public void eliminarCombateTorneo(int id) {

    }

    @Override
    public List<CombateEntrenadorEntidad> obtenerTodosLosParticipantes() {
        return List.of();
    }

    @Override
    public CombateEntrenadorEntidad obtenerParticipantesPorId(int idCombate) {
        return null;
    }
}
