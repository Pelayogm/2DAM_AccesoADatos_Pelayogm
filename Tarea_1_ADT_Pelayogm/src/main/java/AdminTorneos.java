import java.io.Serializable;

public class AdminTorneos extends Usuario implements Serializable {
    private boolean isAdminTorneos = true;
    private String nombreAdminTorneo;
    private String contrasenaAdminTorneo;

    public AdminTorneos() {
       setEstadoSesion(true);
    }

    public AdminTorneos(String contrasenaAdminTorneo, String nombreAdminTorneo) {
        setNombreUsuario(nombreAdminTorneo);
        setEstadoSesion(true);
        this.contrasenaAdminTorneo = contrasenaAdminTorneo;
        this.nombreAdminTorneo = nombreAdminTorneo;
    }
}
