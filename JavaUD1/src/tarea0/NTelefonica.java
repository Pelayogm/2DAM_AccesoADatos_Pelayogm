package tarea0;

import java.util.Date;

public class NTelefonica extends Notificacion {
    private String numero;
    protected long id;
    protected Date fecha;
    protected String mensaje;
    long ms = System.currentTimeMillis();

    public NTelefonica () {
    }

    public NTelefonica(long id, String mensaje, String telefono, Date fecha) {
        super(id, mensaje);
        this.numero = telefono;
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "NTelefonica{" +
                "numero='" + numero + '\'' +
                '}';
    }
}
