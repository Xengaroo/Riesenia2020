public class Trpaslici {
    static long min = 0;
    static int pocet = 0;
    static long max = 0;

    public static void pomocna(int n) {
        //max = 2 * (1 * 1 + 1 * n + 1 * n);
        max = 1;
        max += n;
        max += n;
        max *= 2;

        for (int a = 1; a <= Math.sqrt(n); a++) {
            if (n % a > 0) continue;

            for (int b = a; b <= Math.sqrt(n); b++) {
                if (n % b > 0) continue;
                if ((n/a) % b > 0) continue;

                int c = n / (a*b);
                if (c < b) continue;

                if (a * b * c == n) {
                    //System.out.println("pomocna");
                    min = 2 * (a * b + a * c + b * c);
                    pocet++;
                }
            }
        }
    }

    public static int pocetMoznosti(int n) {
        long kontrola = 1;
        kontrola += n;
        kontrola += n;
        kontrola *= 2;
        if (max != kontrola) {
            min = 0;
            pocet = 0;
        }
        if (pocet == 0) {
            pomocna(n);
        }
        return pocet;
    }

    public static int rozdiel(int n) {
        long kontrola = 1;
        kontrola += n;
        kontrola += n;
        kontrola *= 2;
        if (max != kontrola) {
            min = 0;
            pocet = 0;
        }

        //max = kontrola;
        if (min == 0) {
            pomocna(n);
        }
        //int min_plocha = (int)min;
        long v = max - min;
        int v2 = (int)v;
        return v2;
    }

    public static void main(String[] args) {
        System.out.println(pocetMoznosti(30));
        System.out.println(rozdiel(30));
        System.out.println(pocetMoznosti(20));
        System.out.println(rozdiel(20));
    }
}
