public class Torneo {
    private int id;
    private String nombre;
    private char codRegion;
    private float PuntosVictoria;
    private boolean torneoCreado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Torneo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.torneoCreado = true;
    }
}
