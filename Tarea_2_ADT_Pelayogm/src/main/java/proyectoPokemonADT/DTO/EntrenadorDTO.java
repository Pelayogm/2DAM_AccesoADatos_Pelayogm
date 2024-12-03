package proyectoPokemonADT.DTO;

import proyectoPokemonADT.Usuario;

public class EntrenadorDTO extends Usuario {

    private final int id;
    private final String nombre;
    private final String nacionalidad;
    private final CarnetDTO carnet;

    public EntrenadorDTO(int id, String nombre, String nacionalidad, CarnetDTO carnet) {
        setEstadoSesion(true);
        setUsuario(false);
        setNombreUsuario(nombre);
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.carnet = carnet;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public CarnetDTO getCarnet() {
        return carnet;
    }
}
