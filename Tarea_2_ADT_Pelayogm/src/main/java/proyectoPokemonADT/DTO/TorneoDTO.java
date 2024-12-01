package proyectoPokemonADT.DTO;

public class TorneoDTO {

    private final int id;
    private final String nombre;
    private final char codRegion;
    private final float PuntosVictoria;

    public TorneoDTO(int id, String nombre, char codRegion, float puntosVictoria) {
        this.id = id;
        this.nombre = nombre;
        this.codRegion = codRegion;
        PuntosVictoria = puntosVictoria;
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
}
