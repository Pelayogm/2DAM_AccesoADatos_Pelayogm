package proyectoPokemonADT.Entidades;

import java.sql.Date;

public class CombateEntidad {
    private final int idCombate;
    private final Date fechaCombate;

    public CombateEntidad(int idCombate, Date fechaCombate) {
        this.idCombate = idCombate;
        this.fechaCombate = fechaCombate;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public Date getFechaCombate() {
        return fechaCombate;
    }
}
