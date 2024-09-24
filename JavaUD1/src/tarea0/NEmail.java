package tarea0;

import java.util.Date;

public class NEmail extends Notificacion {
    private String direccion;
    protected long id;
    protected Date fecha;
    protected String mensaje;
    long ms = System.currentTimeMillis();

    public NEmail() {
        super();
    }

    public NEmail(long id, String mensaje, String direccion, Date fecha) {
        super(id, mensaje);
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "NEmail{" +
                "direccion='" + direccion + '\'' +
                '}';
    }
}
