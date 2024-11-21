package servicios;

import DAO.AlumnoDAOImplementacion;
import DTO.AlumnoDTO;
import entidades.AlumnoEntidad;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlumnoServicio {
    private static AlumnoServicio instancia;
    private AlumnoDAOImplementacion alumnoDAOImplementacion;
    private DataSource dataSource;

    private AlumnoServicio (DataSource dataSource) {
        this.alumnoDAOImplementacion = AlumnoDAOImplementacion.getInstancia(dataSource);
    }

    public static AlumnoServicio getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new AlumnoServicio(dataSource);
        }
        return instancia;
    }

    public void crearAlumno (AlumnoDTO alumno) {
        Date edadAlumno = calcularEdadAFecha(alumno.getEdad());
        AlumnoEntidad alumnoEntidad = new AlumnoEntidad(alumno.getIdAlumno(), alumno.getNombreAlumno(), alumno.getApellidosAlumno(), edadAlumno, alumno.getGrupo());
        AlumnoDAOImplementacion.getInstancia(dataSource).crearAlumno(alumnoEntidad);

    }

    public AlumnoDTO obtenerAlumnoPorId (String id) {
        AlumnoEntidad alumnoEntidad = AlumnoDAOImplementacion.getInstancia(dataSource).obtenerAlumnoPorId(id);
        int edadAlumno = calcularEdad(alumnoEntidad.getFechaNacimiento());
        AlumnoDTO alumnoDTO = new AlumnoDTO(alumnoEntidad.getCodAlumno(), alumnoEntidad.getNombreAlumno(), alumnoEntidad.getApellidosAlumno(), edadAlumno, alumnoEntidad.getGrupo());
        return alumnoDTO;
    }

    public List<AlumnoDTO> obtenerTodosLosAlumnos () {
        List<AlumnoEntidad> alumnoEntidades = AlumnoDAOImplementacion.getInstancia(dataSource).obtenerTodosLosAlumnos();
        List<AlumnoDTO> alumnoDTOLista = new ArrayList<>();

        for (int i = 0; i < alumnoEntidades.size(); i++) {
            AlumnoEntidad alumnoEntidadCurrent = alumnoEntidades.get(i);
            int edadAlumno = calcularEdad(alumnoEntidadCurrent.getFechaNacimiento());
            AlumnoDTO alumnoDTO = new AlumnoDTO(alumnoEntidadCurrent.getCodAlumno(), alumnoEntidadCurrent.getNombreAlumno(), alumnoEntidadCurrent.getApellidosAlumno(), edadAlumno, alumnoEntidadCurrent.getGrupo());
            alumnoDTOLista.add(alumnoDTO);
        }
        return alumnoDTOLista;
    }

    public boolean eliminarAlumno (String id) {
        try {
            AlumnoDAOImplementacion.getInstancia(dataSource).eliminarAlumno(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AlumnoDTO actualizarAlumno (String id, AlumnoDTO alumno){
        Date edadAlumno = calcularEdadAFecha(alumno.getEdad());
        AlumnoEntidad alumnoEntidad = new AlumnoEntidad(alumno.getIdAlumno(), alumno.getNombreAlumno(), alumno.getApellidosAlumno(), edadAlumno, alumno.getGrupo());
        AlumnoDAOImplementacion.getInstancia(dataSource).actualizarAlumno(alumnoEntidad);
        AlumnoEntidad alumnoEntidadActualizado = AlumnoDAOImplementacion.getInstancia(dataSource).obtenerAlumnoPorId(id);
        int edadAlumnoActualizada = calcularEdad(alumnoEntidadActualizado.getFechaNacimiento());
        AlumnoDTO alumnoDTO = new AlumnoDTO(alumnoEntidadActualizado.getCodAlumno(), alumnoEntidadActualizado.getNombreAlumno(), alumnoEntidadActualizado.getApellidosAlumno(), edadAlumnoActualizada, alumnoEntidadActualizado.getGrupo());
        return alumnoDTO;

    }

    private AlumnoEntidad mapearDtoAEntidad (AlumnoDTO alumnoDTO) {
        Date dateAlumno = calcularEdadAFecha(alumnoDTO.getEdad());
        return new AlumnoEntidad(alumnoDTO.getIdAlumno(), alumnoDTO.getNombreAlumno(), alumnoDTO.getApellidosAlumno(), dateAlumno, alumnoDTO.getGrupo());
    }

    private AlumnoDTO mapearEntidadADto (AlumnoEntidad alumno) {
        int edad = calcularEdad(alumno.getFechaNacimiento());
        return new AlumnoDTO(alumno.getCodAlumno(), alumno.getNombreAlumno(), alumno.getApellidosAlumno(), edad, alumno.getGrupo());
    }

    private int calcularEdad (Date fechaNacimiento) {
       LocalDate nacimiento = new java.util.Date(
               fechaNacimiento.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       return Period.between(nacimiento, LocalDate.now()).getYears();
    }

    private Date calcularEdadAFecha (int edad) {
        LocalDate localDate = LocalDate.now().minusYears(edad);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


}
