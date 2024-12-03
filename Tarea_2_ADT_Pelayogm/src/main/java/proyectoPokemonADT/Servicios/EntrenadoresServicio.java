package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.Carnet;
import proyectoPokemonADT.DAO.CarnetDAOImplementacion;
import proyectoPokemonADT.DAO.EntrenadorDAOImplementacion;
import proyectoPokemonADT.DTO.CarnetDTO;
import proyectoPokemonADT.DTO.EntrenadorDTO;
import proyectoPokemonADT.Entidades.EntrenadorEntidad;
import proyectoPokemonADT.Entrenador;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class EntrenadoresServicio {

    private static EntrenadoresServicio instancia;
    private static EntrenadorDAOImplementacion entrenadorDAOImplementacion;
    private static CarnetServicio carnetServicio;
    private static DataSource dataSource;

    private EntrenadoresServicio (DataSource dataSource) {
        entrenadorDAOImplementacion = EntrenadorDAOImplementacion.getInstancia(dataSource);
        carnetServicio = CarnetServicio.getInstancia(dataSource);
    }

    public static EntrenadoresServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new EntrenadoresServicio(dataSource);
        }
        return instancia;
    }

    public void crearEntrenador (EntrenadorDTO entrenador) {
        EntrenadorEntidad entrenadorEntidad = new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
        entrenadorDAOImplementacion.crearEntrenador(entrenadorEntidad);
        carnetServicio.crearCarnet(entrenador.getCarnet());
    }

    public EntrenadorDTO obtenerEntrenadorPorId (int id) {
        EntrenadorEntidad entrenadorEntidad = entrenadorDAOImplementacion.obtenerEntrenadorPorId(id);
        CarnetDTO carnet = carnetServicio.obtenerCarnetPorId(id);
        return new EntrenadorDTO(entrenadorEntidad.getIdEntrenador(), entrenadorEntidad.getNombreEntrenador(), entrenadorEntidad.getNacionalidadEntrenador(), carnet);
    }

    public List<EntrenadorDTO> obtenerTodosLosEntrenador () {
        List<EntrenadorEntidad> listaDeEntrenadoresEntidad = entrenadorDAOImplementacion.obtenerTodosLosEntrenadores();
        List<EntrenadorDTO> listaDeEntrenadoresDto = new ArrayList<>();

        for (int i = 0; i < listaDeEntrenadoresEntidad.size(); i++) {
            EntrenadorEntidad entrenadorEntidad = listaDeEntrenadoresEntidad.get(i);
            CarnetDTO carnet = carnetServicio.obtenerCarnetPorId(entrenadorEntidad.getIdEntrenador());
            EntrenadorDTO entrenadorDTO = new EntrenadorDTO(entrenadorEntidad.getIdEntrenador(), entrenadorEntidad.getNombreEntrenador(), entrenadorEntidad.getNacionalidadEntrenador(), carnet);
            listaDeEntrenadoresDto.add(entrenadorDTO);
        }
        return listaDeEntrenadoresDto;
    }

    public boolean eliminarEntrenador (int id) {
        try {
            entrenadorDAOImplementacion.eliminarEntrenador(id);
            carnetServicio.eliminarCarnet(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public EntrenadorDTO actualizarEntrenador (int id, EntrenadorDTO entrenador) {
        EntrenadorEntidad entrenadorEntidad = new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
        entrenadorDAOImplementacion.actualizarEntrenador(entrenadorEntidad);
        EntrenadorEntidad entrenadorEntidadActualizado = entrenadorDAOImplementacion.obtenerEntrenadorPorId(id);
        CarnetDTO carnet = carnetServicio.obtenerCarnetPorId(entrenadorEntidadActualizado.getIdEntrenador());
        return new EntrenadorDTO(entrenadorEntidadActualizado.getIdEntrenador(), entrenadorEntidadActualizado.getNombreEntrenador(), entrenadorEntidadActualizado.getNacionalidadEntrenador(), carnet);
    }

    public EntrenadorEntidad mapearDtoAEntidad (EntrenadorDTO entrenador) {
        return new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
    }

    public EntrenadorDTO mapearEntidadADto (EntrenadorEntidad entrenador) {
        CarnetDTO carnet = carnetServicio.obtenerCarnetPorId(entrenador.getIdEntrenador());
        return new EntrenadorDTO(entrenador.getIdEntrenador(), entrenador.getNombreEntrenador(), entrenador.getNacionalidadEntrenador(), carnet);
    }

    public EntrenadorDTO mapearEntrenadorAEntrenadorDto (Entrenador entrenador, CarnetDTO carnet) {
        int idEntrenador = (int) entrenador.getId();
        return new EntrenadorDTO(idEntrenador, entrenador.getNombre(), entrenador.getNacionalidad(), carnet);
    }

    public Entrenador mapearEntrenadorDtoAEntrenador (EntrenadorDTO entrenador, CarnetDTO carnet) {
        Carnet carnetEntrenador = carnetServicio.mapearCarnetDtoACarnet(carnet);
        return new Entrenador(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad(), carnetEntrenador);
    }
}