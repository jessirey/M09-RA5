public class Main {

    public static void main(String args[]) {

        String msgs[] = ("ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?");
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrat\n------");

        for (int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }


        System.out.println("\nDesxifrat\n------");

        for (String msg : msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desxifraRot13[i]);
        }
    }
}