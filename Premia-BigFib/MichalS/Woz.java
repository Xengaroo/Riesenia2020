import java.math.BigInteger;

public class Woz {
    /** Prve fibonacciho cisla do indexu 8 mam ulozenych v poli first8. Potom pomocou for cyklu zistim, ake
     *  vzorce na vypocet budem musiet postupne pouzit, co si zapisujem v premennej s:
     *   - ak je n neparne, zapisem si '+' a pouzijem vzorce:
     *      F[2j+2] = (2 * F[j] + F[j+1]) * F[j+1]
     *      F[2j+1] = F[j]^2 + F[j+1]^2
     *   - ak je n parne, zapisem si '-' a pouzijem vzorce:
     *      F[2j+1] = F[j]^2 + F[j+1]^2
     *      F[2j] = (2 * F[j+1] - F[j]) * F[j]
     *  V cykle delim n cislom 2, pokym n > 7. Ak je n mensie, hodnoty zoberiem z first8 a vypocitam dalsie.
     *  Na konci mi staci vyratat iba F[j], nepotrebujem vyratat aj F[j+1].
     */

    private static String[] first8 = new String[] {"0", "1", "1", "2", "3", "5", "8", "13", "21"};

    public static String bigFib(long n) {
        if (n < 8) {
            return first8[(int)n];
        }

        StringBuilder s = new StringBuilder();
        long x = n;

        while (x > 7) {
            if (x % 2 == 0) {
                s.append('-');
            }
            else {
                s.append('+');
            }
            x /= 2;
        }

        BigInteger big1 = new BigInteger(first8[(int)x]);
        BigInteger big2 = new BigInteger(first8[(int)x + 1]);
        BigInteger big3;

        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == '+') {
                big3 = big2.pow(2).add(big1.pow(2));
                big2 = (big1.add(big1).add(big2)).multiply(big2);
                big1 = big3;
            }
            else {
                big3 = ((big2.add(big2)).subtract(big1)).multiply(big1);
                big2 = big2.pow(2).add(big1.pow(2));
                big1 = big3;
            }
        }

        if (s.charAt(0) == '+') {
            big3 = big2.pow(2).add(big1.pow(2));
            return big3.toString();
        }
        big3 = ((big2.add(big2)).subtract(big1)).multiply(big1);
        return big3.toString();
    }
}
