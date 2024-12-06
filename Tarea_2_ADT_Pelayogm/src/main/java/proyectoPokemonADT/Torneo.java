package proyectoPokemonADT;

import proyectoPokemonADT.Administradores.AdminTorneos;
import proyectoPokemonADT.DTO.CombateDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Torneo implements Serializable {
    private final int id;
    private final String nombre;
    private final char codRegion;
    private final float PuntosVictoria;
    private AdminTorneos adminTorneos;
    //Boolean para saber si esta activo el torneo
    private boolean torneoCreado;
    private ArrayList <Entrenador> participantesDelTorneo = new ArrayList<>();
    private List <CombateDTO> combatesDelTorneo = new ArrayList<>();

    public ArrayList<Entrenador> getParticipantesDelTorneo() {
        return participantesDelTorneo;
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

    public char getCodRegion() {
        return codRegion;
    }

    public boolean isTorneoCreado() {
        return torneoCreado;
    }

    public void setTorneoCreado(boolean torneoCreado) {
        this.torneoCreado = torneoCreado;
    }

    public Torneo(int id, String nombre, char codRegion, float puntosVictoria, List<CombateDTO> combateDTO) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        PuntosVictoria = puntosVictoria;
        this.combatesDelTorneo = combateDTO;
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

    public List<CombateDTO> getCombatesDelTorneo() {
        return combatesDelTorneo;
    }

    public void setCombatesDelTorneo(List<CombateDTO> combatesDelTorneo) {
        this.combatesDelTorneo = combatesDelTorneo;
    }
}
