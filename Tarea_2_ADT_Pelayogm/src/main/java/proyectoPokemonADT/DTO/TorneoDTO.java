package proyectoPokemonADT.DTO;

import java.util.List;

public class TorneoDTO {

    private final int id;
    private final String nombre;
    private final char codRegion;
    private final float PuntosVictoria;
    private final List<CombateDTO> combatesDelTorneo;

    public TorneoDTO(int id, String nombre, char codRegion, float puntosVictoria, List<CombateDTO> combatesDelTorneo) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        PuntosVictoria = puntosVictoria;
        this.combatesDelTorneo = combatesDelTorneo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public char getCodRegion() {
        return codRegion;
    }

    public float getPuntosVictoria() {
        return PuntosVictoria;
    }

    public List<CombateDTO> getCombatesDelTorneo() {
        return combatesDelTorneo;
    }
}
