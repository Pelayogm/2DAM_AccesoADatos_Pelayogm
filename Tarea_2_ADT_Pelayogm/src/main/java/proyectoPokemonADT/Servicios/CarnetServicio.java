package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.DAO.CarnetDAOImplementacion;
import proyectoPokemonADT.DAO.EntrenadorDAOImplementacion;
import proyectoPokemonADT.DTO.CarnetDTO;
import proyectoPokemonADT.DTO.EntrenadorDTO;
import proyectoPokemonADT.Entidades.CarnetEntidad;

import javax.sql.DataSource;
import java.util.List;

public class CarnetServicio {

    private static CarnetServicio instancia;
    private static CarnetDAOImplementacion carnetDAOImplementacion;
    private static DataSource dataSource;

    private CarnetServicio (DataSource dataSource) {
        carnetDAOImplementacion = CarnetDAOImplementacion.getInstancia(dataSource);
    }

    public static CarnetServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CarnetServicio(dataSource);
        }
        return instancia;
    }

    public void crearCarnet (CarnetDTO carnet) {

    }
    public CarnetDTO obtenerCarnetPorId (int id) {
        return null;
    }
    public List<CarnetDTO> obtenerTodosLosCarnet () {
        return null;
    }
    public boolean eliminarCarnet (int id) {
        return false;
    }
    public CarnetDTO actualizarCarnet (int id, CarnetDTO carnet) {
        return null;
    }
    public CarnetEntidad mapearDtoAEntidad (CarnetDTO carnet) {
        return null;
    }

    public EntrenadorDTO mapearEntidadADto (CarnetEntidad carnet) {
       return null;
    }

}
