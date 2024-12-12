package proyectoPokemonADT.DTO;

public class GimnasioDTO {

    private final int idGimnasio;
    private final String nombreGimnasio;
    private final String tipoGimnasio;
    private final int nivelGimnasio;
    private final TorneoDTO torneoDelGimnasio;

    public GimnasioDTO(int idGimnasio, String nombreGimnasio, String tipoGimnasio, int nivelGimnasio, TorneoDTO torneoDelGimnasio) {
        this.idGimnasio = idGimnasio;
        this.nombreGimnasio = nombreGimnasio;
        this.tipoGimnasio = tipoGimnasio;
        this.nivelGimnasio = nivelGimnasio;
        this.torneoDelGimnasio = torneoDelGimnasio;
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

    public TorneoDTO getTorneoDelGimnasio() {
        return torneoDelGimnasio;
    }
}
