public class Suciny {
    public static long[] suciny(long[] a) {
        if (a == null) return null;

        long[] b = new long[a.length];
        long sucin = 1;

        for (int i = 0; i < a.length; i++) {
            b[i] = sucin;
            sucin *= a[i];
        }

        sucin = 1;

        for (int i = a.length - 1; i >= 0; i--) {
            b[i] *= sucin;
            sucin *= a[i];
        }

        return b;
    }
}
