
public class Empleado {
    String apellido;
    int numero;
    double salario;

    public Empleado (String apellido, int numero, double salario) {
        this.apellido = apellido;
        this.numero = numero;
        this.salario = salario;
    }

    public String getApellido() {
        return apellido;
    }

    public int getNumero() {
        return numero;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "apellido='" + apellido + '\'' +
                ", numero=" + numero +
                ", salario=" + salario +
                '}';
    }
}
