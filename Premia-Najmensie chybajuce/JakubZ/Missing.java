import java.util.Arrays;

public class Missing {

    public static int minMissing(int[] pole) {
        for ( int i = 0; i < pole.length; i++ ) {
            while ( pole[i] >= 0 && pole[i] < pole.length && pole[pole[i]] != pole[i] ) {
//                System.out.println(Arrays.toString(pole) + "." + i);
                int temp = pole[pole[i]];
                pole[pole[i]] = pole[i];
                pole[i] = temp;
            }
//            if ( pole[i] >= 0 && pole[i] < pole.length ) {
//                int temp = pole[pole[i]];
//                pole[pole[i]] = pole[i];
//                pole[i] = temp;
//            }
//            System.out.println(Arrays.toString(pole));
        }
        for ( int j = 0; j < pole.length; j++ ) {
            if ( pole[j] != j ) {
                return j;
            }
        }
//        System.out.println("result: ");
        return pole[pole.length-1]+1;
    }

    public static void main(String[] args) {
        System.out.println(minMissing(new int[] {13,17,0,3,7,2,8,18,7,1,16,11,16,20,14,1,16,13,2,13,10,3,8,17,20,7,20,16,19,5,18,9,3,9,1,6,2,13,16,5,10,1,9,10,7,18,10,7,7,20,15,13,18,2,18,12,15,4,4,15,4,9,8,1,6,15,9,16,8,17,5,20,14,19,4,12,21,22,3,1,20,18,12,2,10,9,4,16,11,9,8,13,19,14,4,8,18,16,12,19,5,11,2,13,19,8,10,4,20,17,12,5,4,7,17,4,5,13,11,3,19,16,3,11,17,5,19,6,13,7,2,16,18,16,4,13,18,3,2,4,10,17,13,10,10,17,16,1,9,8,13,13,18,5,9,14,15,6,8,8,11,18,19,7,19,8,18,1,10,20,18,11,9,8,19,1,2,12,4,11,8,8,16,17,16,17,9,13,16,4,8,4,2,7,19,3,17,1,15,6,3,4,11})); //0
        System.out.println(minMissing(new int[] {2,0,1})); //3
        System.out.println(minMissing(new int[] {4,-1,0,1})); //2
        System.out.println(minMissing(new int[] {4,-1,99,-5,8,2,2,0,1})); //3
        System.out.println(minMissing(new int[] {17,18,10,19,20,88,2,3,7,5,6,4,-1,8,-8,11,9,11,15,14,13,12,46,55,55,77,0,0,0,12,12,16,9,9,9,0,1})); //21
    }
}
