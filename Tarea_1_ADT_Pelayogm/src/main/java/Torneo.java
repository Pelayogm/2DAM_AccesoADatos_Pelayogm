import java.util.ArrayList;
import java.util.List;

public class Torneo {
    private int id;
    private String nombre;
    private char codRegion;
    private float PuntosVictoria;
    //Boolean para saber si esta activo el torneo
    private boolean torneoCreado;
    private List <Entrenador> participantesDelTorneo = new ArrayList<>();

    public List<Entrenador> getParticipantesDelTorneo() {
        return participantesDelTorneo;
    }

    public void setParticipantesDelTorneo(List<Entrenador> participantesDelTorneo) {
        this.participantesDelTorneo = participantesDelTorneo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPuntosVictoria() {
        return PuntosVictoria;
    }

    public void setPuntosVictoria(float puntosVictoria) {
        PuntosVictoria = puntosVictoria;
    }

    public char getCodRegion() {
        return codRegion;
    }

    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
    }

    public boolean isTorneoCreado() {
        return torneoCreado;
    }

    public void setTorneoCreado(boolean torneoCreado) {
        this.torneoCreado = torneoCreado;
    }

    public Torneo(String nombre, int id, char codRegion, float puntosVictoria) {
        this.nombre = nombre;
        this.id = id;
        this.codRegion = codRegion;
        PuntosVictoria = puntosVictoria;
        torneoCreado = true;
    }

    public Torneo(int id, String nombre, char codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codigo;
        this.torneoCreado = true;
    }
}
