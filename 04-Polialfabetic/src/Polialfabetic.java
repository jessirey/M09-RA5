import java.util.*;

public class Polialfabetic {

    private static final String ALFABETO = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char [] ALFABET = ALFABETO.toUpperCase().toCharArray();
    private static Random random;
    private static long clauSecreta = 56238;
    public static char[] PERMUTAT;


    public static void permutaAlfabet() {

        PERMUTAT = new char[ALFABET.length];
        List<Character> permutado = new ArrayList<>();

        for(int i = 0; i < ALFABET.length; i++) {
            permutado.add(ALFABET[i]);
        }

        Collections.shuffle(permutado, random);

        for (int i = 0; i < PERMUTAT.length; i++) {
            PERMUTAT[i] = permutado.get(i);
        }
    }

    public static String xifraPoliAlfa(String msg) {

        String resultado = "";

        for (int i = 0; i < msg.length(); i++ ) {

            char caracter = msg.charAt(i);
            permutaAlfabet();

            resultado += transformar(caracter, ALFABET, PERMUTAT);
        }
        return resultado;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        String resultado = "";

        for (int i = 0; i < msgXifrat.length(); i++ ) {

            char caracter = msgXifrat.charAt(i);
            permutaAlfabet();

            resultado += transformar(caracter, PERMUTAT, ALFABET);
        }
        return  resultado;
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

    public static void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }


    public static void main(String args[]) {


        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifratge:\n------");

        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }


        System.out.println("\nDesxifratge:\n------");

        for (int i= 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }

    }
}
