import java.util.Random;

public class Tsunami {
    public static long plochaVody(int[] pohorie) {
        int max = 0;
        for(int i = 0; i < pohorie.length; i++) {
            if(max < pohorie[i]) max = pohorie[i];
        }

        long sum = 0;
        for(int i = 0; i < max; i++) {
            int last = -1;
            for(int j = 0; j < pohorie.length; j++) {
                if(pohorie[j] - i > 0) {
                    if(last != -1)
                        sum += j - last - 1;
                    last = j;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(plochaVody(new int[]{2,1,2}));  // 1
        System.out.println(plochaVody(new int[]{3, 0, 1, 3, 0, 5})); // 8
        System.out.println(plochaVody(new int[]{3,0,0,2,0,4}));  // 10
        System.out.println(plochaVody(new int[]{1, 0, 2, 1, 0, 1, 3, 2, 1,2,1}));  // 6
        Random rnd = new Random();
        int huge[] = new int[100_000_000];
        for(int i = 0; i<huge.length; i++) huge[i] = rnd.nextInt(100);
        System.out.println(plochaVody(huge)); // napr. 4950130618
    }
}
