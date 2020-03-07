public class Missing {
    public static int minMissing(int[] pole){
        int maxcislo = 0;
        int mincislo = 1000000;
        int nieje =0;
        for (int i =0;i<pole.length;i++){
            if (pole[i] > maxcislo){
                maxcislo = pole[i];
            }
        }
        for (int i =0;i<pole.length;i++){
            if ((pole[i] < mincislo) && (pole[i]>=0)){
                mincislo = pole[i];
            }
        }
        if (mincislo >0){
            return 0;
        }
        for (int i =0;i<maxcislo;i++) {
            for (int k =0;k<pole.length;k++) {
                if (i == pole[k]){
                    nieje = 1;
                }
            }
            if (nieje ==0) {
                return i;
            }
            nieje=0;
        }

        return maxcislo +1;

    }

    public static void main(String[] args) {
        System.out.println(minMissing(new int[]{3, 4, -1, 1}));
        System.out.println(minMissing(new int[]{3, 0, 4, -1, 1}));
        System.out.println(minMissing(new int[]{1, 2, 0}));
    }
}
