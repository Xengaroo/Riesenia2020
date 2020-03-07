import java.util.Arrays;

public class Sucet {
    public static boolean sucet(int[] pole, int k){
        Arrays.sort(pole);
        int p = 0;
        for(int i = pole.length - 1;i >=0 ; i--) {
            p = pole[i];

            if (Arrays.binarySearch(pole, k - p) > 0) {
                if (Arrays.binarySearch(pole, k - p) != i){
                return true;}
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 19));
    }
}
