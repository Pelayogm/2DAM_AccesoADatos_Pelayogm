package DTO;

import java.io.Serializable;
import java.util.List;

public class ProfesorDTO implements Serializable {
    private String idProfesor;
    private String nombreProfesor;
    private String ciudad;
    private List<String> modulosProfesor;

    public ProfesorDTO(String idProfesor, String nombreProfesor, String ciudad, List<String> modulosProfesor) {
        this.idProfesor = idProfesor;
        this.nombreProfesor = nombreProfesor;
        this.ciudad = ciudad;
        this.modulosProfesor = modulosProfesor;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public String getCiudad() {
        return ciudad;
    }

    public List<String> getModulosProfesor() {
        return modulosProfesor;
    }
}
