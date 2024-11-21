package entidades;
import java.util.Date;

public class AlumnoEntidad {
    private final String codAlumno;
    private final String nombreAlumno;
    private final String apellidosAlumno;
    private final Date fechaNacimiento;
    private final char grupo;

    public AlumnoEntidad(String codAlumno, String nomAlumno, String apeAlumno, Date dateAlumno, char grupoAlumno) {
        this.codAlumno = codAlumno;
        this.nombreAlumno = nomAlumno;
        this.apellidosAlumno = apeAlumno;
        this.fechaNacimiento = dateAlumno;
        this.grupo = grupoAlumno;
    }

    public String getCodAlumno() {
        return codAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getApellidosAlumno() {
        return apellidosAlumno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public char getGrupo() {
        return grupo;
    }

}
