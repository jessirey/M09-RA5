public class Monoalfabetic {

    private static final String ALFABETO = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char [] ALFABET = ALFABETO.toUpperCase().toCharArray();
    private static final char [] PERMUTAT = permutaAlfabet(ALFABET);

    private static char[] permutaAlfabet(char[] ALFABET) {

        char[] permutado = new char[ALFABET.length];

        for(int i = 0; i < ALFABET.length; i++) {
            permutado[i] = ALFABET[i];
        }

        for (int i = 0; i < ALFABET.length; i++) {
            int j = (int) (Math.random() * ALFABET.length);

            char aux = permutado[i];
            permutado[i] = permutado[j];
            permutado[j] = aux;
        }

        return permutado;
    }

    public static String xifraMonoAlfa(String cadena) {
        
        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);
        }
        return resultado;
    }

    public static String desxifraMonoAlfa(String cadena) {

        String resultado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

        }
        return resultado;
    }



    private static char transformar(char caracter, char[] alfabetoInicial, char[] alfabetoFinal) {

        char caracterMay = Character.toUpperCase(caracter);

        for (int i = 0; i < alfabetoInicial.length; i++) {
            if (caracterMay == alfabetoInicial[i]) {
                if (Character.isLowerCase(caracter)) {
                    return Character.toLowerCase(alfabetoFinal[i]);
                } else {
                    return alfabetoFinal[i];
                }
            }
        }
        return caracter;
    }

    public static void main(String args[]) {

        String tests[] = {"Test 01 arbritre, coixi, Perímetre", "Test 02 Taüll, DiA, año", "Test 03 Peça, Orrius, Bòvila"};

        System.out.println("Xifratge:");

        for (int i = 0; i < tests.length; i++) {

        }


        System.out.println("Desxifratge:");

        for (int i= 0; i < tests.length; i++) {

            
        }


    }
}
