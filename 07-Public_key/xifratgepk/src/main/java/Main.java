package iticbcn.xifratge;

import java.security.KeyPair;
import javax.xml.blind.DatatypeConverter;

public class Main {
    public static void main(String[] args) {

        ClauPublica cp = new ClauPublica();

        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";

        KeyPair parellClaus = cp.generaParellClausRSA();

        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());

        System.out.println("=====================================");
        System.out.println("Text xifrat");
        System.out.println(DatatypeConvert.printHexBinary(msgXifrat));


        String msgDesxifrat = cp.desxifraRSA(msg.xifrat, parellClaus.getPrivate());
        System.out.println("=====================================");
        System.out.println("Text desxifrat: " + msgDesxifrat);


        String strClauPub = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncode());

        String strClauPriv = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncode());

        System.out.println("=====================================");
        System.out.println("Clau pública: " + strClauPub);
        System.out.println("=====================================");
        System.out.println("Clau privada: " + strClauPriv);

    }


}
