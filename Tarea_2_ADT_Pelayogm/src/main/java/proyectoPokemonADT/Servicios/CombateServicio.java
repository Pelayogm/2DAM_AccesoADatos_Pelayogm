package proyectoPokemonADT.Servicios;

import proyectoPokemonADT.Carnet;
import proyectoPokemonADT.DAO.CombateDAOImplementacion;
import proyectoPokemonADT.DAO.CombateEntrenadorDAOImplementacion;
import proyectoPokemonADT.DTO.CombateDTO;
import proyectoPokemonADT.Entidades.CombateEntidad;
import proyectoPokemonADT.Entidades.CombateEntrenadorEntidad;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CombateServicio {

    private static CombateServicio instancia;
    private static CombateDAOImplementacion combateDAOImplementacion;
    private static CombateEntrenadorDAOImplementacion combateEntrenadorDAOImplementacion;
    private static DataSource dataSource;

    private CombateServicio(DataSource dataSource) {
        combateDAOImplementacion = CombateDAOImplementacion.getInstancia(dataSource);
        combateEntrenadorDAOImplementacion = CombateEntrenadorDAOImplementacion.getInstancia(dataSource);
    }

    public static CombateServicio getInstancia (DataSource dataSource) {
        if (instancia == null) {
            instancia = new CombateServicio(dataSource);
        }
        return instancia;
    }

    public void crearCombate (CombateDTO combate) {
        Date fechaCombate = Date.valueOf(combate.getFecha());
        CombateEntidad combateEntidad = new CombateEntidad((int) combate.getId(), fechaCombate, combate.getIdTorneo());
        combateDAOImplementacion.crearCombate(combateEntidad);
        instancia.crearCombateEntrenador(combate);
    }

    public void crearCombateEntrenador (CombateDTO combateDTO) {
        CombateEntrenadorEntidad combateEntrenadorEntidad = instancia.mapearCombateDtoACombateEntrenador(combateDTO);
        combateEntrenadorDAOImplementacion.crearCombateTorneo(combateEntrenadorEntidad);
    }

    public CombateDTO obtenerCombatePorId (int id) {
        CombateEntidad combateEntidad = combateDAOImplementacion.obtenerCombatePorId(id);
        LocalDate fechaCombate = combateEntidad.getFechaCombate().toLocalDate();
        long idCombate = combateEntidad.getIdCombate();
        return new CombateDTO(fechaCombate, idCombate, combateEntidad.getIdTorneo());
    }

    public List<CombateDTO> obtenerTodosLosCombates() {
        List<CombateEntidad> listaDeCombateEntidad = combateDAOImplementacion.obtenerTodosLosCombates();
        List<CombateDTO> listaDeCombateDto = new ArrayList<>();

        for (int i = 0; i < listaDeCombateEntidad.size(); i++) {
            CombateEntidad combateEntidadActual = listaDeCombateEntidad.get(i);
            LocalDate fechaCombate = combateEntidadActual.getFechaCombate().toLocalDate();
            long idCombate = combateEntidadActual.getIdCombate();
            int idTorneo = combateEntidadActual.getIdTorneo();

            CombateDTO combateDTO = new CombateDTO(fechaCombate, idCombate, idTorneo);
            listaDeCombateDto.add(combateDTO);
        }
        return listaDeCombateDto;
    }

    public List<CombateDTO> obtenerTodosLosCombatesDelTorneo (int id) {
        List<CombateEntidad> listaDeCombateEntidad = combateDAOImplementacion.obtenerTodosLosCombatesDeUnTorneo(id);
        List<CombateDTO> listaDeCombateDto = new ArrayList<>();

        for (int i = 0; i < listaDeCombateEntidad.size(); i++) {
            CombateEntidad combateEntidadActual = listaDeCombateEntidad.get(i);
            LocalDate fechaCombate = combateEntidadActual.getFechaCombate().toLocalDate();
            long idCombate = combateEntidadActual.getIdCombate();
            int idTorneo = combateEntidadActual.getIdTorneo();

            CombateDTO combateDTO = new CombateDTO(fechaCombate, idCombate, idTorneo);
            listaDeCombateDto.add(combateDTO);
        }
        return listaDeCombateDto;
    }

    public CombateEntrenadorEntidad mapearCombateDtoACombateEntrenador (CombateDTO combateDTO) {
        int idCombate = (int) combateDTO.getId();
        return new CombateEntrenadorEntidad(idCombate, 1,1);
    }

}
