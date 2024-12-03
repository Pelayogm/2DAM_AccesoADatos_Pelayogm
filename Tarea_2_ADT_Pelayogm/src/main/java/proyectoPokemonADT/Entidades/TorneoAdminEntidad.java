package proyectoPokemonADT.Entidades;

public class TorneoAdminEntidad {

    private final int idTorneo;
    private final int idAdminTorneo;

    public TorneoAdminEntidad(int idTorneo, int idAdminTorneo) {
        this.idTorneo = idTorneo;
        this.idAdminTorneo = idAdminTorneo;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public int getIdAdminTorneo() {
        return idAdminTorneo;
    }

}
