package proyectoPokemonADT.DTO;

import java.time.LocalDate;

public class CombateDTO {
    private final LocalDate fecha;
    private final long id;
    private final int idTorneo;

    public CombateDTO(LocalDate fechaCombate, long idCombate, int idTorneo) {
        this.fecha = fechaCombate;
        this.id = idCombate;
        this.idTorneo = idTorneo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public long getId() {
        return id;
    }

    public int getIdTorneo() {
        return idTorneo;
    }
}
