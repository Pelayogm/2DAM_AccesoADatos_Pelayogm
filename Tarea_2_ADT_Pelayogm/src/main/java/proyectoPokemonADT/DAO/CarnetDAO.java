package proyectoPokemonADT.DAO;

import proyectoPokemonADT.Entidades.CarnetEntidad;

import java.util.List;

public interface CarnetDAO {
    void crearCarnet (CarnetEntidad carnet);
    List<CarnetEntidad> obtenerTodosLosCarnets();
    CarnetEntidad obtenerCarnetPorId(int id);
    void actualizarCarnet (CarnetEntidad carnet);
    void eliminarCarnet (int id);
}
