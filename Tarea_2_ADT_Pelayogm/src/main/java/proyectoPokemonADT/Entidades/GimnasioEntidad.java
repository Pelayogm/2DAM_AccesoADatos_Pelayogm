package proyectoPokemonADT.Entidades;

public class GimnasioEntidad {

    private final int idGimnasio;
    private final String nombreGimnasio;
    private final String tipoGimnasio;
    private final int nivelGimnasio;

    public GimnasioEntidad(int idGimnasio, String nombreGimnasio, String tipoGimnasio, int nivelGimnasio) {
        this.idGimnasio = idGimnasio;
        this.nombreGimnasio = nombreGimnasio;
        this.tipoGimnasio = tipoGimnasio;
        this.nivelGimnasio = nivelGimnasio;
    }

    public int getIdGimnasio() {
        return idGimnasio;
    }

    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    public String getTipoGimnasio() {
        return tipoGimnasio;
    }

    public int getNivelGimnasio() {
        return nivelGimnasio;
    }
}
