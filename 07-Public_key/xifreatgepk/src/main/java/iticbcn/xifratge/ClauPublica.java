package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {

        KeyPairGenerator keypairGen = KeyPairGenerator.getInstance("RSA");
        keypairGen.initialize(2048);

        return keypairGen.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        byte[] xifraMsg = msg.getBytes(StandardCharsets.UTF_8);

        return cipher.doFinal(xifraMsg);

    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);

        byte[] desxifra = cipher.doFinal(msgXifrat);
        String desxifraMsg = new String(desxifra,  StandardCharsets.UTF_8);
        

        return desxifraMsg;
    }

}
