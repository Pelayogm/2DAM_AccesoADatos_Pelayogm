import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
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

    public Carnet() {
    }

    @XmlElement(name = "id")
    public long getIdEntrenador() {
        return idEntrenador;
    }

    @XmlElement(name = "fechaexp")
    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    @XmlElementWrapper(name = "entrenador")

    public float getPuntos() {
        return puntos;
    }

    public int getNumVictorias() {
        return numVictorias;
    }
}
