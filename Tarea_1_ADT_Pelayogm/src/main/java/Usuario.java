public abstract class Usuario {
    private boolean usuario;
    private boolean estadoSesion;

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
}
