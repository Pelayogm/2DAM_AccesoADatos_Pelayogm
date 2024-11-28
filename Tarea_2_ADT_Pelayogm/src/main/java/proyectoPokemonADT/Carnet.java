package proyectoPokemonADT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;


public class Carnet implements Serializable {

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

    public Carnet(long idEntrenador, LocalDate fechaExpedicion) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = fechaExpedicion;
    }

    public long getIdEntrenador() {
        return idEntrenador;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public float getPuntos() {
        return puntos;
    }

    public int getNumVictorias() {
        return numVictorias;
    }
}
