package proyectoPokemonADT.DTO;

import java.time.LocalDate;

public class CombateDTO {
    private final LocalDate fecha;
    private final long id;

    public CombateDTO(LocalDate fechaCombate, long idCombate) {
        this.fecha = fechaCombate;
        this.id = idCombate;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public long getId() {
        return id;
    }
}
