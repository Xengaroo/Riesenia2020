public class Mince {

    public static boolean zoberPrvu(int[] mince) {
        int start = 0, end = 0;
        for ( int i = 0, j = mince.length-1; i < mince.length && j >= 0; i++, j-- ) {
            if ( i % 2 == 0 ) {
                start += mince[i];
            }
            if ( j % 2 == 1 ) {
                end += mince[j];
            }
        }
        return start > end;
    }

    public static void main(String[] args) {
        System.out.println(zoberPrvu(new int[] {1,2,3,4}));
        System.out.println(zoberPrvu(new int[] {8,2,7,4}));
        System.out.println(zoberPrvu(new int[] {8,2,7,5,3,4}));
        System.out.println(zoberPrvu(new int[] {8,9,7,5,3,4}));
    }
}
