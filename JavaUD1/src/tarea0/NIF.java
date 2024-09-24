package tarea0;

public class NIF extends Documentacion {
    private String numero;
    private char letraFinal;


    public NIF() {
    }

    public NIF (String numero, char letraFinal) {
        this.numero = numero;
        this.letraFinal = letraFinal;
    }

    public String getNumero() {
        return numero;
    }

    public char getLetraFinal() {
        return letraFinal;
    }

    @Override
    public boolean validar(Documentacion nif) {

        if (((NIF)nif).toString().length() != 9) {
            return false;
        } else {
            String nifCompleto = nif.toString();
            for (int i = 0; i < nifCompleto.length() - 1; i++) {
                if (!isNumeric(String.valueOf(nifCompleto.charAt(i)))) {
                    return false;
                }
            }
            String ultimoCaracter = String.valueOf(nifCompleto.charAt(nifCompleto.length() - 1));
            return !isNumeric(ultimoCaracter);
        }
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false; //Error no es numerico
        }
        return true; //Es numerico
    }

    @Override
    public String toString() {
        return numero + letraFinal;
    }

    @Override
    public String mostrar(Documentacion nif) {
        return "NIF{" +
                "numero='" + numero + '\'' +
                ", letraFinal=" + letraFinal +
                '}';
    }
}
