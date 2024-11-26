package entidades;

import java.util.ArrayList;
import java.util.List;

public class ProfesorEntidad {
    private final String codProfesor;
    private final String nombreProfesor;
    private final String residencia;

    public ProfesorEntidad(String codProfesor, String nombreProfesor, String residencia) {
        this.codProfesor = codProfesor;
        this.nombreProfesor = nombreProfesor;
        this.residencia = residencia;
    }

    public String getCodProfesor() {
        return codProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public String getResidencia() {
        return residencia;
    }


}
