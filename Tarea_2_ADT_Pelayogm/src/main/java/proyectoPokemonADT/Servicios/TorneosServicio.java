package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.DAO.TorneoAdminDAOImplementacion;
import proyectoPokemonADT.DAO.TorneoDAOImplementacion;
import proyectoPokemonADT.DTO.CombateDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Entidades.TorneoAdminEntidad;
import proyectoPokemonADT.Entidades.TorneoEntidad;
import proyectoPokemonADT.Torneo;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class TorneosServicio {

    private static TorneosServicio instancia;
    private static TorneoDAOImplementacion torneoDAOImplementacion;
    private static TorneoAdminDAOImplementacion torneoAdminDAOImplementacion;
    private static CombateServicio combateServicio;
    private static DataSource dataSource;

    private TorneosServicio (DataSource dataSource) {
        torneoDAOImplementacion = TorneoDAOImplementacion.getInstancia(dataSource);
        combateServicio = CombateServicio.getInstancia(dataSource);
        torneoAdminDAOImplementacion = TorneoAdminDAOImplementacion.getInstancia(dataSource);
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
        TorneoAdminEntidad torneoAdminEntidad = mapearTorneoDTOATorneoAdminEntidad(torneo);
        torneoDAOImplementacion.crearTorneo(torneoEntidad);
        for (int i = 0; i < torneo.getCombatesDelTorneo().size(); i++) {
            CombateDTO combate = torneo.getCombatesDelTorneo().get(i);
            combateServicio.crearCombate(combate);
        }
        torneoAdminDAOImplementacion.crearTorneoAdmin(torneoAdminEntidad);
    }

    public TorneoDTO obtenerTorneoPorId (int id) {
        TorneoEntidad torneoEntidad = torneoDAOImplementacion.obtenerTorneoPorId(id);
        List<CombateDTO> combatesDelTorneo = combateServicio.obtenerTodosLosCombatesDelTorneo(id);
        char codigoTorneo = torneoEntidad.getCodigoTorneo().charAt(0);
        float puntosTorneo = (float) torneoEntidad.getPuntosVictoriaTorneo();
        int idAdminTorneo = torneoAdminDAOImplementacion.obtenerAdminTorneoPorId(id);
        return new TorneoDTO(torneoEntidad.getIdTorneo(), torneoEntidad.getNombreTorneo(), codigoTorneo, puntosTorneo, combatesDelTorneo, idAdminTorneo);
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
            int idAdminTorneo = torneoAdminDAOImplementacion.obtenerAdminTorneoPorId(idTorneo);

            TorneoDTO torneoDTO = new TorneoDTO(idTorneo, torneoEntidad.getNombreTorneo(), codigoTorneo, puntosTorneo, combatesTorneo, idAdminTorneo);
            listaDeTorneoDTO.add(torneoDTO);
        }
        return listaDeTorneoDTO;
    }

    public TorneoDTO actualizarTorneo (int id, TorneoDTO torneo) {
        return null;
    }

    public TorneoEntidad mapearDtoAEntidad (TorneoDTO torneo) {
        String idTorneo = String.valueOf(torneo.getCodRegion());
        return new TorneoEntidad(torneo.getId(), torneo.getNombre(),idTorneo, torneo.getPuntosVictoria());
    }

    public TorneoDTO mapearEntidadADto (TorneoEntidad torneo) {
        char codigoTorneo = torneo.getCodigoTorneo().charAt(0);
        float puntosTorneo = (float) torneo.getPuntosVictoriaTorneo();
        List<CombateDTO> combatesTorneo = combateServicio.obtenerTodosLosCombatesDelTorneo(torneo.getIdTorneo());
        int idAdminTorneo = torneoAdminDAOImplementacion.obtenerAdminTorneoPorId(torneo.getIdTorneo());
        return new TorneoDTO(torneo.getIdTorneo(), torneo.getNombreTorneo(), codigoTorneo, puntosTorneo, combatesTorneo, idAdminTorneo);
    }

    public TorneoDTO mapearTorneoDTOaTorneo (Torneo torneo, long idAdminTorneo) {
        int idAdminTorneos = (int) idAdminTorneo;
        return new TorneoDTO(torneo.getId(), torneo.getNombre(), torneo.getCodRegion(), torneo.getPuntosVictoria(), torneo.getCombatesDelTorneo(), idAdminTorneos);
    }

    public TorneoAdminEntidad mapearTorneoDTOATorneoAdminEntidad (TorneoDTO torneo) {
        return new TorneoAdminEntidad(torneo.getId(), torneo.getIdAdminTorneos());
    }
}
