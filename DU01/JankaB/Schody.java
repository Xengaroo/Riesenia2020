public class Schody {
    public static int pocet12(int n) {
        if (n <= 2) {    // potrebujeme vediet prve dva cleny
            return n;
        }
        return pocet12(n-1) + pocet12(n-2);
    }


    public static int pocet123(int n) {
        if (n <= 2) {    // potrebujeme vediet prve 3 cleny
            return n;
        }
        if (n == 3) {
            return 4;
        }
        return pocet123(n-1) + pocet123(n-2) + pocet123(n-3);
    }

    public static void main(String[] args) {
        for (int j = 1; j < 10; j++) {
            System.out.println(pocet12(j));
        }
        System.out.println("druheeee");
        for (int i = 1; i < 10; i++) {
            System.out.println(pocet123(i));
        }
    }
}
