public class AdminTorneos extends Usuario {
    private String nombreAdminTorneo;
    private String contrasenaAdminTorneo;

    public AdminTorneos(String contrasenaAdminTorneo, String nombreAdminTorneo) {
        this.contrasenaAdminTorneo = contrasenaAdminTorneo;
        this.nombreAdminTorneo = nombreAdminTorneo;
    }
}
