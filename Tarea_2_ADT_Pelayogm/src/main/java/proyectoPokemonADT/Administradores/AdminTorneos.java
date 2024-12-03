package proyectoPokemonADT.Administradores;
import proyectoPokemonADT.*;
import java.io.Serializable;

public class AdminTorneos extends Usuario implements Serializable {
    private long idUsuario;
    private boolean isAdminTorneos = true;
    private String nombreAdminTorneo;
    private String contrasenaAdminTorneo;

    public AdminTorneos() {
       setEstadoSesion(true);
    }

    public AdminTorneos(String contrasenaAdminTorneo, String nombreAdminTorneo, long idUsuario) {
        setNombreUsuario(nombreAdminTorneo);
        setEstadoSesion(true);
        this.idUsuario = idUsuario;
        this.contrasenaAdminTorneo = contrasenaAdminTorneo;
        this.nombreAdminTorneo = nombreAdminTorneo;
    }

    public AdminTorneos(String contrasenaAdminTorneo, String nombreAdminTorneo) {
        this.contrasenaAdminTorneo = contrasenaAdminTorneo;
        this.nombreAdminTorneo = nombreAdminTorneo;
    }

    public long getIdUsuario() {
        return idUsuario;
    }
}
