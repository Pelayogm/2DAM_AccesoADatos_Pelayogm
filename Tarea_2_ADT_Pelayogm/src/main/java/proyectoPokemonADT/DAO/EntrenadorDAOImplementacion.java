package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.EntrenadorEntidad;

import javax.sql.DataSource;
import java.util.List;

public class EntrenadorDAOImplementacion implements EntrenadorDAO {

    private static EntrenadorDAOImplementacion instancia;
    private DataSource dataSource;

    private EntrenadorDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EntrenadorDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new EntrenadorDAOImplementacion(dataSource);
        }
        return instancia;
    }

    @Override
    public void crearEntrenador(EntrenadorEntidad entrenador) {

    }

    @Override
    public List<EntrenadorEntidad> obtenerTodosLosEntrenadores() {
        return List.of();
    }

    @Override
    public EntrenadorEntidad obtenerEntrenadorPorId(int id) {
        return null;
    }

    @Override
    public void actualizarEntrenador(EntrenadorEntidad entrenador) {

    }

    @Override
    public void eliminarEntrenador(int id) {

    }
}
