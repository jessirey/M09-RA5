
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



public class Hashes {

    int npass = 0;

    private static final String CHARSET = "abcdefABCDEF1234567890!";

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {

        MessageDigest msgDigest = MessageDigest.getInstance("SHA-512");
        
        msgDigest.update(salt.getBytes());

        byte[] arrayBytes = msgDigest.digest(pw.getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(arrayBytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {

        int longitudClave = 512;
        int interaciones = 65536;
        
        char[] charPw = pw.toCharArray();
        byte[] bytesSalt = salt.getBytes();

        PBEKeySpec pbeKeySpec = new PBEKeySpec(charPw, bytesSalt, interaciones, longitudClave);

        SecretKeyFactory secretKey = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] arrayBytes = secretKey.generateSecret(pbeKeySpec).getEncoded();


        HexFormat hex = HexFormat.of();
        return hex.formatHex(arrayBytes);
    }

    public String forcaBruta(String alg, String hash, String salt) {
        
        char[] charset = CHARSET.toCharArray();
        char[] pw = new char[6];
        npass = 0;

        String resultado;

        for (char caracter1 : charset) {
            pw[0] = caracter1;

            resultado = verificarChar(alg, pw, 1, salt, hash);
            if (resultado != null) return resultado;
            
                for(char caracter2 : charset) {
                    pw[1] = caracter2;
                    
                    resultado = verificarChar(alg, pw, 2,salt, hash);
                    if (resultado != null) return resultado;

                        for(char caracter3 : charset) {

                            pw[2]= caracter3;
                        
                            resultado = verificarChar(alg, pw, 3,salt, hash);
                            if (resultado != null) return resultado;

                                for(char caracter4 : charset) {

                                pw[3] = caracter4;
                            
                                resultado = verificarChar(alg, pw, 4,salt, hash);
                                if (resultado != null) return resultado;
                                    
                                    for(char caracter5 : charset) {

                                        pw[4] = caracter5;
                                    
                                        resultado = verificarChar(alg, pw, 5,salt, hash);
                                        if (resultado != null) return resultado;

                                            for(char caracter6 : charset) {

                                            pw[5] = caracter6;
                                        
                                            resultado = verificarChar(alg, pw, 6,salt, hash);
                                            if (resultado != null) return resultado;
                                            }
                                    }
                                 }
                        }
                }
        }
        return null;    

    }

    private String verificarChar(String alg, char[] caractersPw, int longitud,  String salt, String hashResultado) {

        npass++;
        String resultadoPw = new String(caractersPw, 0, longitud);
        try {
            
            String hashPw;
            if (alg.equals("SHA-512")) {
                hashPw = getSHA512AmbSalt(resultadoPw, salt);
            } else {
                hashPw = getPBKDF2AmbSalt(resultadoPw, salt);
            }

            if  (hashPw.equalsIgnoreCase(hashResultado)) {
               return resultadoPw;
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }



    public String getInterval(long t1, long t2) {


        long milisengundos = t2 - t1;

        long dia = milisengundos / 86_400_000;
        long hora = (milisengundos % 86_400_000) / 3_600_000;
        long minuto = (milisengundos % 3_600_000) / 60_000;
        long segundo = (milisengundos % 60_000) / 1_000;
        long milisengundo = milisengundos % 1_000;


        return String.format("%d dies/ %d hores / %d  minuts / %d segons / %d millis", dia,  hora, minuto, segundo, milisengundo);
    }

    public static void main(String[] args) throws Exception {
        
        String salt = "qpoweruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)};

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("=======================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("-------------------------\n");
            System.out.printf("--Inici de força bruta ---\n");


            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();


            
            System.out.printf("Pass: %s\n", pwTrobat);
            System.out.printf("Provats: %s\n", h.npass);
            System.out.printf("Temps: %s\n", h.getInterval(t1, t2));
            System.out.printf("------------------------\n\n");
        }

    }
}
