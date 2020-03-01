
public class Schody {
    public static int pocet12(int n){
        int count = 0;
        int a = 0;
        int b = 1;
        for(int i = 1;i <= n;i++){
            count = a + b;
            a = b;
            b = count;
            System.out.println(i +". ->"+count);
        }
        return count;
    }
    public static int pocet123(int n){
        int count = 0;
        int a = 0;
        int b = 1;
        int c = 1;
        if(n == 1){
            return 1;
        }
        for(int i = 2;i <= n;i++){
            count = a + b + c;
            a = b;
            b = c;
            c = count;
            System.out.println(i +". ->"+count);
        }
        return count;
    }
    // prve dve som spravil pomocou fibbonaciho a nazvymeto fibbonaciho cez 3 premenne ale poslednu
    //som nuteny spravit rekurzivne, inspiraciu som nasiel na internete, ale prototyp som mal hotovy,
    //ale nefungovalo to spravne, hadzalo to timeout.
    // tu je link https://www.geeksforgeeks.org/count-ways-to-reach-the-nth-stair-using-any-step-from-the-given-array
    public static int pocet(int n, int[] kroky){
        int len = kroky.length;
        int count[] = new int[n+1];
        count[0] = 1;
        if(n == 0){
            return 1;
        }
        for(int i=1; i <= n; i++){
            int pocet = 0;
            for(int j = 0; j < len; j++){
                if (i - kroky[j] >= 0) {
                    pocet += count[i - kroky[j]];
                }
                count[i] = pocet;
            }
        }
        return count[n];
    }

    public static void main(String[] args) {
        System.out.println(pocet12(5));
        System.out.println(pocet(5, new int[]{1,2}));
    }
}