package proyectoPokemonADT.Entidades;

public class EntrenadorTorneoEntidad {
    private final int idUsuario;
    private final int idTorneo;

    public EntrenadorTorneoEntidad(int idUsuario, int idTorneo) {
        this.idUsuario = idUsuario;
        this.idTorneo = idTorneo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

}
