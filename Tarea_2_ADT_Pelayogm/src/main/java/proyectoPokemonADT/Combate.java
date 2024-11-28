package proyectoPokemonADT;

import java.time.LocalDate;

public class Combate {
    private LocalDate fecha;
    private long id;

    public Combate(LocalDate fechaCombate, long idCombate) {
        this.fecha = fechaCombate;
        this.id = idCombate;
    }
}
