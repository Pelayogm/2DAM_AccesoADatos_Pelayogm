package proyectoPokemonADT.Entidades;

public class EntrenadorEntidad {
    private final int idEntrenador;
    private final String nombreEntrenador;
    private final String nacionalidadEntrenador;

    public EntrenadorEntidad(int idEntrenador, String nombreEntrenador, String nacionalidadEntrenador) {
        this.idEntrenador = idEntrenador;
        this.nombreEntrenador = nombreEntrenador;
        this.nacionalidadEntrenador = nacionalidadEntrenador;
    }

    public int getIdEntrenador() {
        return idEntrenador;
    }

    public String getNombreEntrenador() {
        return nombreEntrenador;
    }

    public String getNacionalidadEntrenador() {
        return nacionalidadEntrenador;
    }
}
