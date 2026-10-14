package iticbcn.xifratge;

import org.w3c.dom.Text;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ClauKEYNonSimetric";


    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {

        try {
            byte[] creaBytes = msg.getBytes(StandardCharsets.UTF_8);

            iv = generaIv();
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeySpec secretKey = generaHash(clau);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] msgXifrat = cipher.doFinal(creaBytes);

            byte[] resultado = new byte[iv.length + msgXifrat.length];

            System.arraycopy(iv, 0, resultado, 0, iv.length);
            System.arraycopy(msgXifrat, 0, resultado, iv.length, msgXifrat.length);

            return new TextXifrat(resultado);

        }catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }

    }


    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {

        try {
            byte[] bIvIMsgXifrat = xifrat.getBytes();
            byte[] ivSolo = extreureIv(bIvIMsgXifrat);

            byte[] bytesXifrat = getBytesXifrats(bIvIMsgXifrat);

            SecretKeySpec secretKey = generaHash(clau);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            IvParameterSpec ivSpec = new IvParameterSpec(ivSolo);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] bytesDesxifrats = cipher.doFinal(bytesXifrat);

            return new String(bytesDesxifrats, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    public byte[] generaIv() {

        SecureRandom generaAleatorio = new SecureRandom();

        byte[] nuevoIv = new byte[MIDA_IV];

        generaAleatorio.nextBytes(nuevoIv);
        return nuevoIv;
    }

    public SecretKeySpec generaHash(String clau) throws Exception {

        MessageDigest algoritmo = MessageDigest.getInstance(ALGORISME_HASH);

        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);

        byte[] hashClau = algoritmo.digest(bytesClau);
        SecretKeySpec secretKey = new SecretKeySpec(hashClau, ALGORISME_XIFRAT);

        return secretKey;
    }

    public byte[] extreureIv(byte[] bIvIMsgXifrat) {

        byte[] ivSolo = new byte[MIDA_IV];

        System.arraycopy(bIvIMsgXifrat, 0, ivSolo, 0, MIDA_IV);

        return ivSolo;

    }

    public byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {

        int longitud = bIvIMsgXifrat.length - MIDA_IV;

        byte[] bytesXifrats = new byte[longitud];

        System.arraycopy(bIvIMsgXifrat, MIDA_IV, bytesXifrats, 0, longitud);

        return bytesXifrats;

    }

    /*

    public static void main (String[] args) {

        String msgs[] = {"Lorem ipsum dicet",
                           "Hola Andrés cómo está tu cuñado",
                            "Àgora ïlla Ôtto"};
        for (int i = 0; i <msgs.length; i++) {

            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " +
                        e.getLocalizedMessage());
            }

            System.out.println("--------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    } */
}