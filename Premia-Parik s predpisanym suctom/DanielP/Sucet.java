import java.util.Arrays;
public class Sucet {
    public static boolean sucet(int[] pole, int k) {
        Arrays.sort(pole);
        int left = 0; int right = pole.length - 1;
        while(left < right) {
            int sum = pole[left] + pole[right];
            if(sum == k) {
                return true;
            }
            else if(sum < k) {
                left++;
            }
            else if(sum > k) {
                right--;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 19));
        System.out.println(sucet(new int[]{11, 16, 3, 17}, 22));
    }
}

// nevedel som ako vyriesit ulohu s malou zlozitostou, tak som si pomohol :
//https://javarevisited.blogspot.com/2014/08/how-to-find-all-pairs-in-array-of-integers-whose-sum-equal-given-number-java.html