public class Nasobok {
    /** Princip: Ak je na konci 0, zapis 0, odtrhni od konca a chod na dalsie cislo.
     *  Ak je cislo parne, vynasob 5 - dostaneme 0 na konci. Ak je na konci 5,
     *  vynasob 2 - dostaneme 0 na konci. Ak cislo konci 1, 3, 7, alebo 9, hladaj
     *  take cislo zlozene iba z jednotiek, ktore je delitelne vstupnym cislom.
     */

    private static String naKonci1379(int n) {
        StringBuilder s = new StringBuilder();
        int x = n;

        while (x > 0) {
            for (int i = 0; i < 10; i++) {
                if (x % 10 == 1) {
                    s.append('1');
                    x /= 10;
                    break;
                }
                x += n;
            }
        }

        return s.toString();
    }

    public static String nasobok10(int n) {
        if (n == 0) return "0";

        StringBuilder vysl = new StringBuilder();

        while (n > 0) {
            if (n % 10 == 0) {
                vysl.insert(0, '0');
                n /= 10;
            }
            else if (n % 2 == 0) {
                n *= 5;
            }
            else if (n % 10 == 5) {
                n *= 2;
            }
            else {
                vysl.insert(0, naKonci1379(n));
                n = 0;
            }
        }

        return vysl.toString();
    }

    /*
    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println(i + ": " + nasobok10(i));
        }
    }
    */
}
