package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.Carnet;
import proyectoPokemonADT.DAO.EntrenadorDAOImplementacion;
import proyectoPokemonADT.DAO.EntrenadorTorneoDAOImplementacion;
import proyectoPokemonADT.DTO.CarnetDTO;
import proyectoPokemonADT.DTO.EntrenadorDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Entidades.EntrenadorEntidad;
import proyectoPokemonADT.Entrenador;
import proyectoPokemonADT.Torneo;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class EntrenadoresServicio {

    private static EntrenadoresServicio instancia;
    private static EntrenadorDAOImplementacion entrenadorDAOImplementacion;
    private static CarnetServicio carnetServicio;
    private static EntrenadorTorneoDAOImplementacion entrenadorTorneoDAOImplementacion;
    private static TorneosServicio torneosServicio;
    private static DataSource dataSource;

    private EntrenadoresServicio (DataSource dataSource) {
        entrenadorDAOImplementacion = EntrenadorDAOImplementacion.getInstancia(dataSource);
        carnetServicio = CarnetServicio.getInstancia(dataSource);
        entrenadorTorneoDAOImplementacion = EntrenadorTorneoDAOImplementacion.getInstancia(dataSource);
        torneosServicio = TorneosServicio.getInstancia(dataSource);
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
        List<Integer> idDeLosTorneos = entrenadorTorneoDAOImplementacion.obtenerTorneosDeUnEntrenador(id);
        List<TorneoDTO> torneosDelUsuario = new ArrayList<>();
        for (int i = 0; i < idDeLosTorneos.size(); i++) {
            torneosDelUsuario.add(torneosServicio.obtenerTorneoPorId(idDeLosTorneos.get(i)));
        }

        return new EntrenadorDTO(entrenadorEntidad.getIdEntrenador(), entrenadorEntidad.getNombreEntrenador(), entrenadorEntidad.getNacionalidadEntrenador(), carnet, torneosDelUsuario);
    }

    public List<EntrenadorDTO> obtenerTodosLosEntrenador () {
        List<EntrenadorEntidad> listaDeEntrenadoresEntidad = entrenadorDAOImplementacion.obtenerTodosLosEntrenadores();
        List<EntrenadorDTO> listaDeEntrenadoresDto = new ArrayList<>();

        for (int i = 0; i < listaDeEntrenadoresEntidad.size(); i++) {
            EntrenadorEntidad entrenadorEntidad = listaDeEntrenadoresEntidad.get(i);
            CarnetDTO carnet = carnetServicio.obtenerCarnetPorId(entrenadorEntidad.getIdEntrenador());

            List<Integer> idDeLosTorneos = entrenadorTorneoDAOImplementacion.obtenerTorneosDeUnEntrenador(entrenadorEntidad.getIdEntrenador());
            List<TorneoDTO> torneosDelUsuario = new ArrayList<>();
                for (int x = 0; x < idDeLosTorneos.size(); x++) {
                    torneosDelUsuario.add(torneosServicio.obtenerTorneoPorId(idDeLosTorneos.get(x)));
                }

            EntrenadorDTO entrenadorDTO = new EntrenadorDTO(entrenadorEntidad.getIdEntrenador(), entrenadorEntidad.getNombreEntrenador(), entrenadorEntidad.getNacionalidadEntrenador(), carnet, torneosDelUsuario);
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

        List<Integer> idDeLosTorneos = entrenadorTorneoDAOImplementacion.obtenerTorneosDeUnEntrenador(id);
        List<TorneoDTO> torneosDelUsuario = new ArrayList<>();
        for (int i = 0; i < idDeLosTorneos.size(); i++) {
            torneosDelUsuario.add(torneosServicio.obtenerTorneoPorId(idDeLosTorneos.get(i)));
        }

        return new EntrenadorDTO(entrenadorEntidadActualizado.getIdEntrenador(), entrenadorEntidadActualizado.getNombreEntrenador(), entrenadorEntidadActualizado.getNacionalidadEntrenador(), carnet, torneosDelUsuario);
    }

    public EntrenadorEntidad mapearDtoAEntidad (EntrenadorDTO entrenador) {
        return new EntrenadorEntidad(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad());
    }

    public EntrenadorDTO mapearEntidadADto (EntrenadorEntidad entrenador) {
        CarnetDTO carnet = carnetServicio.obtenerCarnetPorId(entrenador.getIdEntrenador());
        List<Integer> idDeLosTorneos = entrenadorTorneoDAOImplementacion.obtenerTorneosDeUnEntrenador(entrenador.getIdEntrenador());
        List<TorneoDTO> torneosDelUsuario = new ArrayList<>();
        for (int i = 0; i < idDeLosTorneos.size(); i++) {
            torneosDelUsuario.add(torneosServicio.obtenerTorneoPorId(idDeLosTorneos.get(i)));
        }
        return new EntrenadorDTO(entrenador.getIdEntrenador(), entrenador.getNombreEntrenador(), entrenador.getNacionalidadEntrenador(), carnet, torneosDelUsuario);
    }

    public EntrenadorDTO mapearEntrenadorAEntrenadorDto (Entrenador entrenador, CarnetDTO carnet) {
        int idEntrenador = (int) entrenador.getId();
        List<Integer> idDeLosTorneos = entrenadorTorneoDAOImplementacion.obtenerTorneosDeUnEntrenador(idEntrenador);
        List<TorneoDTO> torneosDelUsuario = new ArrayList<>();
        for (int i = 0; i < idDeLosTorneos.size(); i++) {
            torneosDelUsuario.add(torneosServicio.obtenerTorneoPorId(idDeLosTorneos.get(i)));
        }
        return new EntrenadorDTO(idEntrenador, entrenador.getNombre(), entrenador.getNacionalidad(), carnet, torneosDelUsuario);
    }

    public Entrenador mapearEntrenadorDtoAEntrenador (EntrenadorDTO entrenador, CarnetDTO carnet) {
        Carnet carnetEntrenador = carnetServicio.mapearCarnetDtoACarnet(carnet);
        List<Integer> idDeLosTorneos = entrenadorTorneoDAOImplementacion.obtenerTorneosDeUnEntrenador(entrenador.getId());

        List<TorneoDTO> torneosDelUsuario = new ArrayList<>();
        for (int i = 0; i < idDeLosTorneos.size(); i++) {
            torneosDelUsuario.add(torneosServicio.obtenerTorneoPorId(idDeLosTorneos.get(i)));
        }

        ArrayList<Torneo> listaDeTorneos = new ArrayList<>();
        for (int x = 0; x < torneosDelUsuario.size(); x++) {
            TorneoDTO torneoDTOActual = torneosDelUsuario.get(x);
            listaDeTorneos.add(torneosServicio.mapearDtoATorneo(torneoDTOActual));
        }
        Entrenador entrenadorReturn = new Entrenador(entrenador.getId(), entrenador.getNombre(), entrenador.getNacionalidad(), carnetEntrenador);
        entrenadorReturn.setTorneosDelEntrenador(listaDeTorneos);
        return entrenadorReturn;
    }
}
