package iticbcn.xifratge;

import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {

    private static final String ALFABETO = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char [] ALFABET = ALFABETO.toUpperCase().toCharArray();
    private char [] PERMUTAT;

    public XifradorMonoalfabetic() {
        permutaAlfabet();
    }

    private void permutaAlfabet() {

        Random random = new Random();
        PERMUTAT = new char[ALFABET.length];

        for(int i = 0; i < ALFABET.length; i++) {
            PERMUTAT[i] = ALFABET[i];
        }

        for (int i = 0; i < PERMUTAT.length; i++) {
            int j = random.nextInt(PERMUTAT.length);

            char aux = PERMUTAT[i];
            PERMUTAT[i] = PERMUTAT[j];
            PERMUTAT[j] = aux;
        }
    }

    @Override
    public TextXifrat xifra(String cadena, String clau) throws ClauNoSuportada {

        if(clau != null) {
           throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            resultado += transformar(caracter, ALFABET, PERMUTAT);
        }
        return new TextXifrat(resultado.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada{

        if(clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        String cadena = xifrat.toString();
        String resultado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            resultado += transformar(caracter, PERMUTAT, ALFABET);
        }
        return resultado;
    }

    public void mostrarAlfabeto(char[] ALFABET) {

        for (int i = 0; i < ALFABET.length; i++) {
            System.out.print(ALFABET[i]);
        }
        System.out.println();
    }

    public void mostrarPermutado(char[] PERMUTAT) {

        for (int i = 0; i < PERMUTAT.length; i++) {
            System.out.print(PERMUTAT[i]);
        }
        System.out.println();
    }

    private char transformar(char caracter, char[] alfabetoInicial, char[] alfabetoFinal) {

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
    /*
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

        */
}
