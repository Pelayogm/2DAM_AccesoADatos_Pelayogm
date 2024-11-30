package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CombateEntidad;

import javax.sql.DataSource;
import java.util.List;

public class CombateDAOImplementacion implements CombateDAO {

    private static CombateDAOImplementacion instancia;
    private DataSource dataSource;

    private CombateDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CombateDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CombateDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearCombate(CombateEntidad combate) {

    }

    @Override
    public List<CombateEntidad> obtenerTodosLosCombates() {
        return List.of();
    }

    @Override
    public CombateEntidad obtenerCombatePorId(int id) {
        return null;
    }

    @Override
    public void actualiarCombate(CombateEntidad combate) {

    }

    @Override
    public void eliminarCombate(int id) {

    }
}
