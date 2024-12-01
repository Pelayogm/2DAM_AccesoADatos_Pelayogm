package proyectoPokemonADT.DTO;

public class EntrenadorDTO {

    private final int id;
    private final String nombre;
    private final String nacionalidad;

    public EntrenadorDTO(int id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
}
