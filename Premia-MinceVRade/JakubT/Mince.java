public class Mince {
    public static boolean zoberPrvu(int[] mince) {
        int parne = 0;
        int neparne = 0;

        for(int i = 0; i < mince.length; i++){
            if(i % 2 == 0) parne += mince[i];
            else neparne += mince[i];
        }
        if (parne >= neparne) return true;
        else return false;
    }
}
