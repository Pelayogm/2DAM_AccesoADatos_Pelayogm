import java.time.LocalDate;

public class Carnet {
    private long idEntrenador;
    private LocalDate fechaExpedicion;
    private float puntos;
    private int numVictorias;

    public Carnet(long idEntrenador, LocalDate fechaExpedicion, float puntos, int numVictorias) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = fechaExpedicion;
        this.puntos = puntos;
        this.numVictorias = numVictorias;
    }
}
