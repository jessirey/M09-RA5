import java.util.*;

public class Monoalfabetic {

    private static final String ALFABETO = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char [] ALFABET = ALFABETO.toUpperCase().toCharArray();
    private static final char [] PERMUTAT = permutaAlfabet(ALFABET);

    private static char[] permutaAlfabet(char[] ALFABET) {

        char[] permutado = new char[ALFABET.length];
        List<Character> permutat = new ArrayList<>();

        for(int i = 0; i < ALFABET.length; i++) {
            permutat.add(ALFABET[i]);
        }

        Collections.shuffle(permutat);

        for (int i = 0; i < permutat.size(); i++) {
            permutado[i] = permutat.get(i);
        }

        return permutado;
    }

    public static String xifraMonoAlfa(String cadena) {
        
        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            resultado += transformar(caracter, ALFABET, PERMUTAT);
        }
        return resultado;
    }

    public static String desxifraMonoAlfa(String cadena) {

        String resultado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            resultado += transformar(caracter, PERMUTAT, ALFABET);
        }
        return resultado;
    }

    public static void mostrarAlfabeto(char[] ALFABET) {

        for (int i = 0; i < ALFABET.length; i++) {
            System.out.print(ALFABET[i]);
        }
        System.out.println();
    }

    public static void mostrarPermutado(char[] PERMUTAT) {

        for (int i = 0; i < PERMUTAT.length; i++) {
            System.out.print(PERMUTAT[i]);
        }
        System.out.println();
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

        mostrarAlfabeto(ALFABET);
        mostrarPermutado(PERMUTAT);

        String tests[] = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};

        System.out.println("Xifratge:");

        for (int i = 0; i < tests.length; i++) {
            System.out.printf("%-40s -> %s%n", tests[i], xifraMonoAlfa(tests[i]));
        }


        System.out.println("Desxifratge:");

        for (int i= 0; i < tests.length; i++) {

            String cifrado = xifraMonoAlfa(tests[i]);
            String descifrado = desxifraMonoAlfa(cifrado);
            System.out.printf("%-40s -> %s%n", cifrado, descifrado);
        }


    }
}
