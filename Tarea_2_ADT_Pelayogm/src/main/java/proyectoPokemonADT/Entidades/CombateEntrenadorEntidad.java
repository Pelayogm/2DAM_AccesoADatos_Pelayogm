package proyectoPokemonADT.Entidades;

public class CombateEntrenadorEntidad {

    private final int idCombate;
    private final int idEntrenador1;
    private final int idEntrenador2;

    public CombateEntrenadorEntidad(int idCombate, int idEntrenador1, int idEntrenador2) {
        this.idCombate = idCombate;
        this.idEntrenador1 = idEntrenador1;
        this.idEntrenador2 = idEntrenador2;
    }

    public int getIdCombate() {
        return idCombate;
    }

    public int getIdEntrenador1() {
        return idEntrenador1;
    }

    public int getIdEntrenador2() {
        return idEntrenador2;
    }

}
