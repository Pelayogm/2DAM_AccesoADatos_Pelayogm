package tarea0;

import java.util.Date;

public class Notificacion {
    protected long id;
    protected Date fecha;
    protected String mensaje;
    long ms = System.currentTimeMillis();

    public Notificacion () {
    }

    public Notificacion(long id, String mensaje) {
        this.id = id;
        this.fecha = new Date(ms);
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", mensaje='" + mensaje + '\'' +
                ", ms=" + ms +
                '}';
    }
}
