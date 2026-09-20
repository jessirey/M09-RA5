import java.util.*;

public class Rot13 {

    private static char [] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
                'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
                'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
                'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    private static char [] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
                'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
                'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
                't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};

    private static Map<Character, Character> mapCifrado = crearMap(13);
    private static Map<Character, Character> mapDescifrado = crearMap(-13);

    private static Map<Character, Character> crearMap(int posicion) {
        Map<Character, Character> mapa = new HashMap<>();

        for (int i = 0; i < majuscules.length; i++) {
            int nuevaPosicion = (i + posicion) % majuscules.length;

            if (nuevaPosicion < 0) {
                nuevaPosicion += majuscules.length;
            }

            mapa.put(majuscules[i], majuscules[nuevaPosicion]);
            mapa.put(minuscules[i], minuscules[nuevaPosicion]);
        }

        return mapa;
    }

    public static String xifraRot13(String cadena) {
        return transformar(cadena, mapCifrado);
    }

    public static String desxifraRot13(String cadena) {
        return transformar(cadena, mapDescifrado);
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
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }


        System.out.println("\nDesxifrat\n------");

        for (String msg: msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desxifraRot13(msg));
        }
    }
}
