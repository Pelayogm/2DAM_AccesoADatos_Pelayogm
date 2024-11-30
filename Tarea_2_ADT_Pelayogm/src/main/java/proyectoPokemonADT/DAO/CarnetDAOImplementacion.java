package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CarnetEntidad;

import javax.sql.DataSource;
import java.util.List;

public class CarnetDAOImplementacion implements CarnetDAO {

    private static CarnetDAOImplementacion instancia;
    private DataSource dataSource;

    private CarnetDAOImplementacion (DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CarnetDAOImplementacion getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CarnetDAOImplementacion(dataSource);
        }
        return instancia;
    }


    @Override
    public void crearCarnet(CarnetEntidad carnet) {

    }

    @Override
    public List<CarnetEntidad> obtenerTodosLosCarnets() {
        return List.of();
    }

    @Override
    public CarnetEntidad obtenerCarnetPorId(int id) {
        return null;
    }

    @Override
    public void actualizarCarnet(CarnetEntidad carnet) {

    }

    @Override
    public void eliminarCarnet(int id) {

    }
}
