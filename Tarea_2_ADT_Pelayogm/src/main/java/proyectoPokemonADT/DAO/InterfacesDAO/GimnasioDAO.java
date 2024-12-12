package proyectoPokemonADT.DAO.InterfacesDAO;

import proyectoPokemonADT.Entidades.GimnasioEntidad;

import java.util.List;

public interface GimnasioDAO {
    void crearGimnasio (GimnasioEntidad gimnasio);
    List<GimnasioEntidad> obtenerTodosLosGimnasios();
    GimnasioEntidad obtenerGimnasioPorId(int id);
    void eliminarGimnasio (int id);

}
