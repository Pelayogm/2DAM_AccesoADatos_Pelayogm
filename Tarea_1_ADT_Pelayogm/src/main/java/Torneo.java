public class Torneo {
    private int id;
    private String nombre;
    private char codRegion;
    private float PuntosVictoria;

    public Torneo(String nombre, int id, char codRegion, float puntosVictoria) {
        this.nombre = nombre;
        this.id = id;
        this.codRegion = codRegion;
        PuntosVictoria = puntosVictoria;
    }
}
