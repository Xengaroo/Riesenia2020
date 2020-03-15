public class Schody {
    public static int pocet12(int n){
        if (n < 2){
            return 1;
        }
        return (pocet12(n-1) + pocet12(n-2));
    }
    public static int pocet123(int n){
        if (n < 0){
            return 0;
        }
        if (n < 2){
            return 1;
        }
        return (pocet123(n-1) + pocet123(n-2) + pocet123(n-3));
    }

    public static void main(String[] args) {
        System.out.println(pocet12(2));
        System.out.println(pocet123(3));
    }
}
