package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.DAO.TorneoDAOImplementacion;
import proyectoPokemonADT.DTO.CombateDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Entidades.TorneoEntidad;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class TorneosServicio {

    private static TorneosServicio instancia;
    private static TorneoDAOImplementacion torneoDAOImplementacion;
    private static CombateServicio combateServicio;
    private static DataSource dataSource;

    private TorneosServicio (DataSource dataSource) {
        torneoDAOImplementacion = TorneoDAOImplementacion.getInstancia(dataSource);
        combateServicio = CombateServicio.getInstancia(dataSource);
    }

    public static TorneosServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneosServicio(dataSource);
        }
        return instancia;
    }

    public void crearTorneo (TorneoDTO torneo) {
        String codigoTorneo = String.valueOf(torneo.getCodRegion());
        TorneoEntidad torneoEntidad = new TorneoEntidad(torneo.getId(), torneo.getNombre(), codigoTorneo, torneo.getPuntosVictoria());
        torneoDAOImplementacion.crearTorneo(torneoEntidad);
    }

    public TorneoDTO obtenerTorneoPorId (int id) {
        TorneoEntidad torneoEntidad = torneoDAOImplementacion.obtenerTorneoPorId(id);
        List<CombateDTO> combatesDelTorneo = combateServicio.obtenerTodosLosCombatesDelTorneo(id);
        char codigoTorneo = torneoEntidad.getCodigoTorneo().charAt(0);
        float puntosTorneo = (float) torneoEntidad.getPuntosVictoriaTorneo();
        return new TorneoDTO(torneoEntidad.getIdTorneo(), torneoEntidad.getNombreTorneo(), codigoTorneo, puntosTorneo, combatesDelTorneo);
    }

    public List<TorneoDTO> obtenerTodosLosTorneos () {
        List<TorneoEntidad> listaDeTorneoEntidad = torneoDAOImplementacion.obtenerTodosLosTorneos();
        List<TorneoDTO> listaDeTorneoDTO = new ArrayList<>();

        for (int i = 0; i < listaDeTorneoEntidad.size(); i++) {
            TorneoEntidad torneoEntidad = listaDeTorneoEntidad.get(i);
            List<CombateDTO> combatesTorneo = combateServicio.obtenerTodosLosCombatesDelTorneo(torneoEntidad.getIdTorneo());
            int idTorneo = torneoEntidad.getIdTorneo();
            char codigoTorneo = torneoEntidad.getCodigoTorneo().charAt(0);
            float puntosTorneo = (float) torneoEntidad.getPuntosVictoriaTorneo();

            TorneoDTO torneoDTO = new TorneoDTO(idTorneo, torneoEntidad.getNombreTorneo(), codigoTorneo, puntosTorneo, combatesTorneo);
            listaDeTorneoDTO.add(torneoDTO);
        }
        return listaDeTorneoDTO;
    }

    public TorneoDTO actualizarTorneo (int id, TorneoDTO torneo) {
        return null;
    }

    public TorneoEntidad mapearDtoAEntidad (TorneoDTO torneo) {
        return null;
    }

    public TorneoDTO mapearEntidadADto (TorneoEntidad torneo) {
        return null;
    }
}
