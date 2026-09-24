import java.util.*;

public class RotX {

    private static final String ALFABETO = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char [] MAJUSCULES = ALFABETO.toUpperCase().toCharArray();
    private static final char [] MINUSCULES = ALFABETO.toCharArray();

    private static final int[] POSICIONES = {0, 2, 4, 6};

    private static Map<Character, Character> crearMap(int posicion, boolean derecha) {
        Map<Character, Character> mapa = new HashMap<>();

        for (int i = 0; i < MAJUSCULES.length; i++) {
            int nuevaPosicion;
            if (derecha) {
                nuevaPosicion = (i + posicion) % MAJUSCULES.length;
            } else {
                nuevaPosicion = (i - posicion) % MAJUSCULES.length;
            }
            if (nuevaPosicion < 0) {
                nuevaPosicion += MAJUSCULES.length;
            }

            mapa.put(MAJUSCULES[i], MAJUSCULES[nuevaPosicion]);
            mapa.put(MINUSCULES[i], MINUSCULES[nuevaPosicion]);
        }

        return mapa;
    }

    public static String xifraRotX(String cadena, int desplaçament) {

        Map<Character, Character> mapa = crearMap(desplaçament, true);
        return transformar(cadena, mapa);
    }

    public static String desxifraRotX(String cadena, int desplaçament) {

        Map<Character, Character> mapa = crearMap(desplaçament, false);
        return transformar(cadena, mapa);
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {

        for(int i = 0; i < MAJUSCULES.length; i++) {

            System.out.println("(" + i + ")->" + desxifraRotX(cadenaXifrada, i));
        }
    }

    private static String transformar(String cadena, Map<Character, Character> mapa) {
        String resultado = "";

        for (int i = 0; i < cadena.length(); i++) {
            char caracter = cadena.charAt(i);

            if (mapa.containsKey(caracter)) {
                resultado += mapa.get(caracter);
            } else {
                resultado += caracter;
            }
        }

        return resultado;
    }

    public static void main(String args[]) {

        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------");

        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRotX(msgs[i], POSICIONES[i % POSICIONES.length]);
            System.out.printf("(%d)-%-23s => %s%n", POSICIONES[i], msgs[i], msgsXifrats[i]);
        }


        System.out.println("\nDesxifrat\n------");

        for (int i= 0; i < msgs.length; i++) {

            String xifratX = msgsXifrats[i];
            msgsXifrats[i] = desxifraRotX(xifratX, POSICIONES[i % POSICIONES.length]);
            System.out.printf("(%d)%-23s => %s%n", POSICIONES[i], xifratX, msgsXifrats[i]);
        }

        String fuerzaB = "Úiüht, úiü wx ùxì ív?";
        System.out.println("\nMissatge xifrat: Úiüht, úiü wx ùxì ív?\n------");

        forcaBrutaRotX(fuerzaB);
    }
}
