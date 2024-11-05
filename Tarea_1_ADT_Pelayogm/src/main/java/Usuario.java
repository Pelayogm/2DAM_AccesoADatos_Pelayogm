import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private boolean usuario;
    private boolean estadoSesion;
    private String nombreUsuario;

    public boolean isEstadoSesion() {
        return estadoSesion;
    }

    public void setEstadoSesion(boolean estadoSesion) {
        this.estadoSesion = estadoSesion;
    }

    protected void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
