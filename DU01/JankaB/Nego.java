public class Nego {
    public static String toNego(int i) {
        //System.out.println("zacinameeeeee");
        String cislo = "";
        int zvysok = 0;

        while (i != 0) {
            zvysok = i % (-2);
            //System.out.println("zv " + zvysok);
            //System.out.println("stare i " + i);
            i /= (-2);
            //System.out.println("nove i " + i);

            // posledny riado musi byt     1:(-2) = 0 zv 1 !!!
            if (zvysok == -1) {
                zvysok = 1;
                i += 1;
            }
            cislo = zvysok + cislo;
        }
        return cislo;
    }

    public static void main(String[] args) {
        System.out.println(toNego(5));
        System.out.println(toNego(4));
        System.out.println(toNego(8));
        System.out.println(toNego(10));
    }
}
