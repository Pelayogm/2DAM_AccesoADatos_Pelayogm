package proyectoPokemonADT.Entidades;

import java.sql.Date;

public class CarnetEntidad {
    private final int idCarnet;
    private final Date fechaExpedicionCarnet;
    private final double puntosCarnet;
    private final int victoriasCarnet;

    public CarnetEntidad(int idCarnet, Date fechaExpedicionCarnet, double puntosCarnet, int victoriasCarnet) {
        this.idCarnet = idCarnet;
        this.fechaExpedicionCarnet = fechaExpedicionCarnet;
        this.puntosCarnet = puntosCarnet;
        this.victoriasCarnet = victoriasCarnet;
    }

    public int getIdCarnet() {
        return idCarnet;
    }

    public Date getFechaExpedicionCarnet() {
        return fechaExpedicionCarnet;
    }

    public double getPuntosCarnet() {
        return puntosCarnet;
    }

    public int getVictoriasCarnet() {
        return victoriasCarnet;
    }
}
