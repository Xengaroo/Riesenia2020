public class Suciny {
    public static long[] suciny(long[] a){
        long []pole = new long [a.length];
        long sucin = 1;
        int pocetNul= 0;
        for (int i = 0; i < a.length;i++){
            if (a[i] == 0){
                pocetNul++;
            }
            if (pocetNul > 1){
                long []pole2 = new long [a.length];
                pole2[i] = 0;
                return pole2;
            }
            for (int j = 0; j < a.length;j++){
                if (i == j){
                    continue;
                }

                sucin *= a[j];
            }
            pole[i] = sucin;
            sucin = 1;


        }
        return pole;
    }
}
