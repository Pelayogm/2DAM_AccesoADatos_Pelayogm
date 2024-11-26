package DTO;

public class ModuloDTO {
    private String cod_modulo;
    private String nombre_modulo;
    private String cod_ciclo;
    private String curso;
    private String cod_profesor;

    public ModuloDTO(String cod_modulo, String nombre_modulo, String cod_ciclo, String curso, String cod_profesor) {
        this.cod_modulo = cod_modulo;
        this.nombre_modulo = nombre_modulo;
        this.cod_ciclo = cod_ciclo;
        this.curso = curso;
        this.cod_profesor = cod_profesor;
    }

    public String getCod_modulo() {
        return cod_modulo;
    }

    public String getNombre_modulo() {
        return nombre_modulo;
    }

    public String getCod_ciclo() {
        return cod_ciclo;
    }

    public String getCurso() {
        return curso;
    }

    public String getCod_profesor() {
        return cod_profesor;
    }
}
