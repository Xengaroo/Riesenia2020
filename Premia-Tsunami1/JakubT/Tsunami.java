import java.util.Arrays;

public class Tsunami {
    public static long plochaVody(int[] pohorie){
        int[] copy = Arrays.copyOf(pohorie,pohorie.length);
        Arrays.sort(copy);

        int max = copy[copy.length-1];
        int actual = pohorie[0];
        int vysl = 0;
        int stack = 0;
        boolean hladina = false;

        for(int i = 1; i<pohorie.length-1;i++){
            if(pohorie[i]+1 >= max){
                actual = pohorie[i];
                continue;
            }
            if(hladina && pohorie[i] <= actual){
                stack += 1;
            }
            if(hladina && pohorie[i]>actual){
                vysl += stack;
                hladina = false;
                continue;
            }
            if(pohorie[i]<actual){
                stack += Math.abs(pohorie[i]-actual);
                actual = pohorie[i];
                hladina = true;
                continue;
            }
            else{
                actual = pohorie[i];
                hladina = false;
            }
        }
        return vysl;
    }

    public static void main(String[] args) {
        System.out.println(plochaVody(new int[]{1, 0, 2, 1, 0, 1, 3, 2, 1,2,1}));
    }
}
