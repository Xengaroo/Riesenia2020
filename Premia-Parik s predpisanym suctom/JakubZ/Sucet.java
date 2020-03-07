public class Sucet {

    public static boolean sucet(int[] pole, int k) {
        int[] temp = new int[k+1];
        for ( int item : pole ) {
            if ( item <= k && temp[item] == 1 ) {
                return true;
            }
            if ( item <= k ) {
                temp[k-item] = 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 19)); // T
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 22)); // F
        System.out.println(sucet(new int[]{0, 1}, 1)); // F
    }
}
