package proyectoPokemonADT;

import proyectoPokemonADT.Administradores.AdminTorneos;

import java.io.Serializable;
import java.util.ArrayList;

public class Torneo implements Serializable {
    private int id;
    private String nombre;
    private char codRegion;
    private float PuntosVictoria;
    private AdminTorneos adminTorneos;
    //Boolean para saber si esta activo el torneo
    private boolean torneoCreado;
    private ArrayList <Entrenador> participantesDelTorneo = new ArrayList<>();

    public ArrayList<Entrenador> getParticipantesDelTorneo() {
        return participantesDelTorneo;
    }

    public void setParticipantesDelTorneo(ArrayList<Entrenador> participantesDelTorneo) {
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

    public Torneo(int id, String nombre, char codRegion, float puntosVictoria) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        PuntosVictoria = puntosVictoria;
        torneoCreado = true;
    }

    public Torneo(int id, String nombre, char codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codigo;
        this.torneoCreado = true;
        this.PuntosVictoria = 100;
    }

    public AdminTorneos getAdminTorneos() {
        return adminTorneos;
    }

    public void setAdminTorneos(AdminTorneos adminTorneos) {
        this.adminTorneos = adminTorneos;
    }
}
