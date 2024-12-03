package proyectoPokemonADT.DTO;

import java.time.LocalDate;

public class CarnetDTO {

    private final long idEntrenador;
    private final LocalDate fechaExpedicionCarnet;
    private final float puntosCarnet;
    private final int numeroVictorias;

    public CarnetDTO(long idEntrenador, LocalDate fechaExpedicionCarnet, float puntosCarnet, int numeroVictorias) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicionCarnet = fechaExpedicionCarnet;
        this.puntosCarnet = puntosCarnet;
        this.numeroVictorias = numeroVictorias;
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

    public int getNumeroVictorias() {
        return numeroVictorias;
    }
}
