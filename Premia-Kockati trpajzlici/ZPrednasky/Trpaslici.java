import java.util.Arrays;
import java.util.TreeSet;

public class Trpaslici {

    static Integer[] delitele(int n) {
        int m = n;
        Integer[] res = new Integer[10];
        int count = 0;
        for (int a = 1; (long) a * a <= m; a++) {
            if (m % a == 0) {
                if (count + 2 > res.length) { // realocate
                    Integer[] newres = new Integer[res.length * 2];
                    System.arraycopy(res, 0, newres, 0, count);
                    res = newres;
                }
                res[count++] = a;
                if (a != n / a)
                    res[count++] = n / a;
            }
        }
        res = Arrays.copyOf(res, count);
        //Arrays.sort(res);  -- netreba
        return res;
    }

    static TreeSet<Integer> deliteleSTreeSet(int n) {
        int m = n;
        TreeSet<Integer> res = new TreeSet<Integer>();
        for (int a = 1; (long) a * a <= m; a++) {
            if (m % a == 0) {
                res.add(a);
                res.add(n / a);
                //m /= a;
            }
        }
        return res;
    }

    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    public static int pocetMoznosti(int n) {
        int count = 0;
        for (Integer a : delitele(n)) {
            for (Integer b : delitele(n / a)) {
                if (b < a) continue;
                int c = n / a / b;
                if (c < b) continue;
                long povrch = 2 * ((long) a * b + a * c + b * c);
                //System.out.printf("%d x %d x %d objem = %d, povrch=%d\n", a, b, c, a*b*c, povrch);
                count++;
                if (povrch > max) max = povrch;
                if (povrch < min) min = povrch;
            }
        }
        return count;
    }

    // stasny hack !!!!!!!!!!!!!!!!!!
    public static long rozdiel(int n) {
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
        pocetMoznosti(n);
        return max - min;
    }
}