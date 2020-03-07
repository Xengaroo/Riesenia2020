import java.util.Arrays;

public class Missing {
//   nie je linearne
    public static int minMissingNlogN(int[] pole) {
        Arrays.sort(pole);
        for(int i = 0; ; i++)
            if (Arrays.binarySearch(pole,i) < 0)
                return i;
    }

    // autor Gyorgi Tomcsanyi
    public static int minMissing(int[] pole) {
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] < 0) { pole[i] = pole.length; }
        }
        for (int i = 0; i < pole.length; i++) {
            int x = Math.abs(pole[i]);
            if (x >= 0 && x < pole.length) {
                pole[x] = - Math.abs(pole[x]);
                if (pole[x] == 0) {
                    pole[0] = -1;
                    pole[x] = -pole.length;
                }
            }
        }
        for (int i = 0; i < pole.length; i++) {
            if (pole[i] >= 0) {
                return i;
            }
        }
        return pole.length;
    }

    // autor PB
    public static int minMissingPB(int[] pole) {
       for(int i = 0; i<pole.length; i++) {
           int kam = pole[i];
           pole[i] = -1;
           while (kam >= 0 && kam < pole.length) {
               int x = pole[kam];
               if (x == kam) break;
               pole[kam] = kam;
               kam = x;
           }
       }
       for(int i = 0; i<pole.length; i++)
           if (pole[i] == -1) return i;
       return pole.length;
    }
    public static void main(String[] args) {
        System.out.println(minMissing(new int[]{3,4,2,1}));
        System.out.println(minMissing(new int[]{3,4,2,0}));
        System.out.println(minMissing(new int[]{3,4,-1,1}));
        System.out.println(minMissing(new int[]{3,0,4,-1,1}));
        System.out.println(minMissing(new int[]{1,2,0}));
    }
}
