package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.TorneoEntidad;

import javax.sql.DataSource;
import java.util.List;

public class TorneoDAOImplementacion implements TorneoDAO {

    private static TorneoDAOImplementacion instancia;
    private DataSource dataSource;

    private TorneoDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static TorneoDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneoDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearTorneo(TorneoEntidad torneo) {

    }

    @Override
    public List<TorneoEntidad> obtenerTodosLosTorneos() {
        return List.of();
    }

    @Override
    public TorneoEntidad obtenerTorneoPorId(int id) {
        return null;
    }

    @Override
    public void actualizarTorneo(TorneoEntidad torneo) {

    }

    @Override
    public void eliminarTorno(int id) {

    }
}
