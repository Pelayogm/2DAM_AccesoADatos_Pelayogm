package proyectoPokemonADT.Entidades;

public class TorneoEntidad {
    private final int idTorneo;
    private final String nombreTorneo;
    private final String codigoTorneo;
    private final double puntosVictoriaTorneo;

    public TorneoEntidad(int idTorneo, String nombreTorneo, String codigoTorneo, double puntosVictoriaTorneo) {
        this.idTorneo = idTorneo;
        this.nombreTorneo = nombreTorneo;
        this.codigoTorneo = codigoTorneo;
        this.puntosVictoriaTorneo = puntosVictoriaTorneo;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public String getCodigoTorneo() {
        return codigoTorneo;
    }

    public double getPuntosVictoriaTorneo() {
        return puntosVictoriaTorneo;
    }
}
