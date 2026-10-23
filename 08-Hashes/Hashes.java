

public class Hashes {

    private final int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) {

        String resultado = "";


        return resultado;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {

        String resultado = "";


        return resultado;
    }

    public String forcaBruta(String alg, String hash, String salt) {
        
        Charsert charset = new Charsert("abcdefABCDEF1234567890!");
        
        String resultado = "";


        return resultado;

    }

    public String getInterval(long t1, long t2) {


        String resultado = "";


        return resultado;
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
