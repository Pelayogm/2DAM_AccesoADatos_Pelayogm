package proyectoPokemonADT.DTO;

import java.time.LocalDate;
import java.util.Date;

public class CarnetDTO {

    private final long idEntrenador;
    private final LocalDate fechaExpedicionCarnet;
    private final float puntosCarnet;
    private final int numeroCarnet;

    public CarnetDTO(long idEntrenador, LocalDate fechaExpedicionCarnet, float puntosCarnet, int numeroCarnet) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicionCarnet = fechaExpedicionCarnet;
        this.puntosCarnet = puntosCarnet;
        this.numeroCarnet = numeroCarnet;
    }

    public long getIdEntrenador() {
        return idEntrenador;
    }

    public LocalDate getFechaExpedicionCarnet() {
        return fechaExpedicionCarnet;
    }

    public float getPuntosCarnet() {
        return puntosCarnet;
    }

    public int getNumeroCarnet() {
        return numeroCarnet;
    }
}
