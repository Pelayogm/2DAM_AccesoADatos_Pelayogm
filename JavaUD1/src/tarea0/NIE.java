package tarea0;

public class NIE extends Documentacion {
    private String numero;
    private char letraInicial;
    private char letraFinal;

    public NIE() {
    }

    public NIE(String numero, char letraInicial, char letraFinal) {
        this.numero = numero;
        this.letraInicial = letraInicial;
        this.letraFinal = letraFinal;
    }

    public String getNumero() {
        return numero;
    }

    public char getLetraInicial() {
        return letraInicial;
    }

    public char getLetraFinal() {
        return letraFinal;
    }

    @Override

    public boolean validar(Documentacion nie) {
        if ((nie).toString().length() > 9) {
            return false;
        } else {
            String nieCompleto = nie.toString();
            String letra;
            letra = String.valueOf(nieCompleto.indexOf(1));
            if (letra.equals("X") || letra.equals("Y") || letra.equals("Z")) {
                for (int i = 1; i < nieCompleto.length() - 1; i++) {
                    if (!isNumeric(String.valueOf(nieCompleto.charAt(i)))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
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
        return letraInicial + numero + letraFinal;
    }

    @Override
    public String mostrar(Documentacion nie) {
        return "NIE{" +
                "numero='" + numero + '\'' +
                ", letraInicial=" + letraInicial +
                ", letraFinal=" + letraFinal +
                '}';
    }
}
