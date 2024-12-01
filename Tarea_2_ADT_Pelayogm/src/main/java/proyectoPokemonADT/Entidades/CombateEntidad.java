package proyectoPokemonADT.Entidades;

import java.sql.Date;

public class CombateEntidad {
    private final int idCombate;
    private final Date fechaCombate;
    private final int idTorneo;

    public CombateEntidad(int idCombate, Date fechaCombate, int idTorneo) {
        this.idCombate = idCombate;
        this.fechaCombate = fechaCombate;
        this.idTorneo = idTorneo;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public Date getFechaCombate() {
        return fechaCombate;
    }

    public int getIdTorneo() {
        return idTorneo;
    }
}
