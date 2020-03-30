public class Cisla {
    private static int sumaDelitelov(int m) {
        int suma = 1;
        int delitel = 2;
        int limit = m / 2 + 1;

        while (delitel < limit) {
            if (m % delitel == 0) {
                suma += delitel;
                limit = m / delitel;
                if (delitel != limit) {
                    suma += limit;
                }
            }
            delitel++;
        }

        return suma;
    }

    public static String spriatelene(int max) {
        StringBuilder out = new StringBuilder();
        int n;

        for (int m = 2; m < max; m++) {
            n = sumaDelitelov(m);
            if (n == m) {
                continue;
            }
            if (m == sumaDelitelov(n)) {
                out.append(' ');
                out.append(m);
            }
        }

        if (out.length() > 0) {
            out.deleteCharAt(0);
        }

        return out.toString();
    }
}
