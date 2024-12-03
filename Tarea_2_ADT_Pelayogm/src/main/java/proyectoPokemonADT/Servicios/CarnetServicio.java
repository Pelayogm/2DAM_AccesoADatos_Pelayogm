package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.DAO.CarnetDAOImplementacion;
import proyectoPokemonADT.DTO.CarnetDTO;
import proyectoPokemonADT.Entidades.CarnetEntidad;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
        Date fechaExpedicionCarnet = Date.valueOf(carnet.getFechaExpedicionCarnet());
        double puntosCarnet = carnet.getPuntosCarnet();
        int idEntrenador = (int) carnet.getIdEntrenador();
        CarnetEntidad carnetEntidad = new CarnetEntidad(idEntrenador, fechaExpedicionCarnet, puntosCarnet, carnet.getNumeroVictorias());
        carnetDAOImplementacion.crearCarnet(carnetEntidad);
    }
    public CarnetDTO obtenerCarnetPorId (int id) {
        CarnetEntidad carnetEntidad = carnetDAOImplementacion.obtenerCarnetPorId(id);
        LocalDate fechaCarnet = carnetEntidad.getFechaExpedicionCarnet().toLocalDate();
        float puntosCarnet = (float) carnetEntidad.getPuntosCarnet();
        long idEntrenador = carnetEntidad.getIdCarnet();

        return new CarnetDTO(idEntrenador, fechaCarnet, puntosCarnet, carnetEntidad.getVictoriasCarnet());
    }

    public List<CarnetDTO> obtenerTodosLosCarnet () {
        List<CarnetEntidad> listaDeCarnetEntidad = carnetDAOImplementacion.obtenerTodosLosCarnets();
        List<CarnetDTO> listaDeCarnetDTO = new ArrayList<>();

        for (int i = 0; i < listaDeCarnetEntidad.size(); i++) {
            CarnetEntidad carnetEntidad = listaDeCarnetEntidad.get(i);
            LocalDate fechaCarnet = carnetEntidad.getFechaExpedicionCarnet().toLocalDate();
            float puntosCarnet = (float) carnetEntidad.getPuntosCarnet();
            long idEntrenador = carnetEntidad.getIdCarnet();

            CarnetDTO carnetDTO = new CarnetDTO(idEntrenador, fechaCarnet, puntosCarnet, carnetEntidad.getVictoriasCarnet());
            listaDeCarnetDTO.add(carnetDTO);
        }
        return listaDeCarnetDTO;
    }

    public boolean eliminarCarnet (int id) {
        try {
            carnetDAOImplementacion.eliminarCarnet(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CarnetDTO actualizarCarnet (int id, CarnetDTO carnet) {
        Date fechaExpedicionCarnet = Date.valueOf(carnet.getFechaExpedicionCarnet());
        double puntosCarnet = carnet.getPuntosCarnet();
        int idEntrenador = (int) carnet.getIdEntrenador();
        CarnetEntidad carnetEntidad = new CarnetEntidad(idEntrenador, fechaExpedicionCarnet, puntosCarnet, carnet.getNumeroVictorias());
        carnetDAOImplementacion.actualizarCarnet(carnetEntidad);

        CarnetEntidad carnetEntidadActualizado = carnetDAOImplementacion.obtenerCarnetPorId(id);
        LocalDate fechaCarnet = carnetEntidadActualizado.getFechaExpedicionCarnet().toLocalDate();
        float puntosCarnetActualiado = (float) carnetEntidadActualizado.getPuntosCarnet();
        long idEntrenadorActualizado = carnetEntidadActualizado.getIdCarnet();

        return new CarnetDTO(idEntrenadorActualizado, fechaCarnet, puntosCarnetActualiado, carnetEntidadActualizado.getVictoriasCarnet());
    }

    public CarnetEntidad mapearDtoAEntidad (CarnetDTO carnet) {
        Date fechaExpedicionCarnet = Date.valueOf(carnet.getFechaExpedicionCarnet());
        double puntosCarnet = carnet.getPuntosCarnet();
        int idEntrenador = (int) carnet.getIdEntrenador();
        return new CarnetEntidad(idEntrenador, fechaExpedicionCarnet, puntosCarnet, carnet.getNumeroVictorias());
    }

    public CarnetDTO mapearEntidadADto (CarnetEntidad carnet) {
        LocalDate fechaCarnet = carnet.getFechaExpedicionCarnet().toLocalDate();
        float puntosCarnet = (float) carnet.getPuntosCarnet();
        long idEntrenador = carnet.getIdCarnet();

        return new CarnetDTO(idEntrenador, fechaCarnet, puntosCarnet, carnet.getVictoriasCarnet());
    }

}
