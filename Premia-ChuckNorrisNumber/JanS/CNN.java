public class CNN {
    public static String toString(long i, int b) {
        String res = "";
        while(i > 0) {
            res = i % b + res;
            i /= b;
        }
        return res;
    }

    public static boolean isPalindrom(String str){
        int len = str.length();
        for (int i = 0; i < len/2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(long n){
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static long cnn(){
        long num = 11;
        while (true){
            if (isPrime(num) && isPalindrom(toString(num, 2))){
                long tmp = num;
                long mun = 0;
                while (tmp != 0) {
                    mun = mun * 10 + tmp % 10;
                    tmp /= 10;
                }
                if (isPrime(mun) && isPalindrom(toString(mun, 2))){
                    return num;
                }
            }
            num++;
        }
    }

    public static void main(String[] args) {
        System.out.println(cnn());
    }
}
