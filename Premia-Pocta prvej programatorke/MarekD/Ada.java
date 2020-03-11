import java.math.BigInteger;


public class Ada {

    public static int ageAL() {
        return 36;
    }

    public static double factorial(double n) {
        if (n < 1) {
            return 1;
        }
        else {
            return n * factorial(n - 1);
        }
    }

    public static double combination(double n, double k){
        if (k <= n){
            return factorial(n) / (factorial(k) * factorial(n - k));
        }
        else{
            return 0;
        }
    }

    public static double bernoulli1(int m) {
        if (m == 0){
            return 1;
        }
        else{
            double t = 0;
            for (int i = 0; i < m; i++) {
                t += combination(m, i) * bernoulli1(i) / (m - i + 1);
            }
            return 1 - t;
        }
    }
    public static double bernoulli(int m) {
        if (m == 1){
            return -0.5;
        }
        if (m % 2 == 1){
            return 0;
        }

        return bernoulli1(m);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20 ; i++) {
            System.out.print(i + " > ");
            System.out.println(bernoulli(i));
        }
    }

}
