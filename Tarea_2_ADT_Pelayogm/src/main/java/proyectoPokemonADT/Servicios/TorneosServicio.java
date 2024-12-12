package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.DAO.*;
import proyectoPokemonADT.DTO.CombateDTO;
import proyectoPokemonADT.DTO.GimnasioDTO;
import proyectoPokemonADT.DTO.TorneoDTO;
import proyectoPokemonADT.Entidades.*;
import proyectoPokemonADT.Torneo;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class TorneosServicio {

    private static TorneosServicio instancia;
    private static TorneoDAOImplementacion torneoDAOImplementacion;
    private static TorneoAdminDAOImplementacion torneoAdminDAOImplementacion;
    private static EntrenadorTorneoDAOImplementacion entrenadorTorneoDAOImplementacion;
    private static CombateEntrenadorDAOImplementacion combateEntrenadorDAOImplementacion;
    private static GimnasioDAOImplementacion gimnasioDAOImplementacion;
    private static CombateServicio combateServicio;
    private static DataSource dataSource;

    private TorneosServicio (DataSource dataSource) {
        torneoDAOImplementacion = TorneoDAOImplementacion.getInstancia(dataSource);
        combateServicio = CombateServicio.getInstancia(dataSource);
        torneoAdminDAOImplementacion = TorneoAdminDAOImplementacion.getInstancia(dataSource);
        entrenadorTorneoDAOImplementacion = EntrenadorTorneoDAOImplementacion.getInstancia(dataSource);
        combateEntrenadorDAOImplementacion = CombateEntrenadorDAOImplementacion.getInstancia(dataSource);
        gimnasioDAOImplementacion = GimnasioDAOImplementacion.getInstancia(dataSource);
    }

    public static TorneosServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneosServicio(dataSource);
        }
        return instancia;
    }

    public void crearTorneo (TorneoDTO torneo, GimnasioEntidad gimnasioEntidad) {
        String codigoTorneo = String.valueOf(torneo.getCodRegion());
        TorneoEntidad torneoEntidad = new TorneoEntidad(torneo.getId(), torneo.getNombre(), codigoTorneo, torneo.getPuntosVictoria());
        TorneoAdminEntidad torneoAdminEntidad = mapearTorneoDTOATorneoAdminEntidad(torneo);
        torneoDAOImplementacion.crearTorneo(torneoEntidad);
        torneoAdminDAOImplementacion.crearTorneoAdmin(torneoAdminEntidad);
        for (int i = 0; i < torneo.getCombatesDelTorneo().size(); i++) {
            CombateDTO combate = torneo.getCombatesDelTorneo().get(i);
            combateServicio.crearCombate(combate);
        }
        gimnasioDAOImplementacion.crearGimnasio(gimnasioEntidad);
    }

    public TorneoDTO obtenerTorneoPorId (int id) {
        TorneoEntidad torneoEntidad = torneoDAOImplementacion.obtenerTorneoPorId(id);
        List<CombateDTO> combatesDelTorneo = combateServicio.obtenerTodosLosCombatesDelTorneo(id);
        char codigoTorneo = torneoEntidad.getCodigoTorneo().charAt(0);
        float puntosTorneo = (float) torneoEntidad.getPuntosVictoriaTorneo();
        int idAdminTorneo = torneoAdminDAOImplementacion.obtenerAdminTorneoPorId(id);
        return new TorneoDTO(torneoEntidad.getIdTorneo(), torneoEntidad.getNombreTorneo(), codigoTorneo, puntosTorneo, combatesDelTorneo, idAdminTorneo);
    }

    public GimnasioDTO obtenerGimnasioPorId (int id) {
        GimnasioEntidad gimnasioEntidad = gimnasioDAOImplementacion.obtenerGimnasioPorId(id);
        return mapearGimnasioEntidadADto(gimnasioEntidad);
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
        TorneoEntidad torneoEntidad = mapearGimnasioDtoAEntidad(torneo);
        torneoDAOImplementacion.actualizarTorneo(torneoEntidad, id);
        return null;
    }

    public boolean actualizarParticipantesTorneo (int id, int idParticipante) {
        try {
            List<CombateDTO> combateDTO = combateServicio.obtenerTodosLosCombatesDelTorneo(id);
            EntrenadorTorneoEntidad entrenadorTorneoEntidad = new EntrenadorTorneoEntidad(idParticipante, id);
            entrenadorTorneoDAOImplementacion.crearEntrenadorTorneo(entrenadorTorneoEntidad);
            //Este contador nos permitirá saber si lo hemos añadido a los combates
            int contador = 0;
            for (int i = 0; i < combateDTO.size(); i++) {
                if (contador < 2) {
                    int idCombate = (int) combateDTO.get(i).getId();
                    List<Integer> participantesCombateActual = combateEntrenadorDAOImplementacion.obtenerParticipantesPorIdDelCombate(idCombate);
                    if (participantesCombateActual.get(0) == 1) {
                        CombateEntrenadorEntidad combateEntrenadorEntidad = new CombateEntrenadorEntidad(idCombate, idParticipante, participantesCombateActual.get(1));
                        combateEntrenadorDAOImplementacion.actualizarCombateEntrenador(idCombate, combateEntrenadorEntidad);
                        contador++;
                    } else if (participantesCombateActual.get(1) == 1) {
                        CombateEntrenadorEntidad combateEntrenadorEntidad = new CombateEntrenadorEntidad(idCombate, participantesCombateActual.get(0), idParticipante);
                        combateEntrenadorDAOImplementacion.actualizarCombateEntrenador(idCombate, combateEntrenadorEntidad);
                        contador++;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public GimnasioEntidad mapearGimnasioDtoAEntidad(GimnasioDTO gimnasio) {
        return new GimnasioEntidad(gimnasio.getIdGimnasio(), gimnasio.getNombreGimnasio(), gimnasio.getTipoGimnasio(), gimnasio.getNivelGimnasio());
    }

    public GimnasioDTO mapearGimnasioEntidadADto(GimnasioEntidad gimnasioEntidad) {
        TorneoDTO torneoDTO = obtenerTorneoPorId(gimnasioEntidad.getIdGimnasio());
        return new GimnasioDTO(gimnasioEntidad.getIdGimnasio(), gimnasioEntidad.getNombreGimnasio(), gimnasioEntidad.getTipoGimnasio(), gimnasioEntidad.getNivelGimnasio(), torneoDTO);
    }

    public TorneoEntidad mapearGimnasioDtoAEntidad(TorneoDTO torneo) {
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

    public TorneoDTO mapearTorneoATorneoDto(Torneo torneo, long idAdminTorneo) {
        int idAdminTorneos = (int) idAdminTorneo;
        return new TorneoDTO(torneo.getId(), torneo.getNombre(), torneo.getCodRegion(), torneo.getPuntosVictoria(), torneo.getCombatesDelTorneo(), idAdminTorneos);
    }

    public Torneo mapearDtoATorneo (TorneoDTO torneo) {
        return new Torneo(torneo.getId(), torneo.getNombre(), torneo.getCodRegion(), torneo.getPuntosVictoria(), torneo.getCombatesDelTorneo());
    }

    public TorneoAdminEntidad mapearTorneoDTOATorneoAdminEntidad (TorneoDTO torneo) {
        return new TorneoAdminEntidad(torneo.getId(), torneo.getIdAdminTorneos());
    }
}
