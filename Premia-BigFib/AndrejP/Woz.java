import java.math.BigInteger;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Woz {

    public static String bigFib(long n){
        Fibo vysl = new Fibo();
        return vysl.toString((int)n);
    }

    public static void main(String[] args) {
        //System.out.println(Fibonacci(10000));
        System.out.println(bigFib(10000000).length());
    }
}

class Fibo{
    HashMap<Integer, BigInteger> mem; //Dictionary memo;
    Fibo(){
        mem = new HashMap<>(); //memo = new Hashtable();
        mem.put(0, BigInteger.valueOf(0));
        mem.put(1, BigInteger.valueOf(1));
        mem.put(2, BigInteger.valueOf(1));
        mem.put(3, BigInteger.valueOf(2));
        mem.put(4, BigInteger.valueOf(3));
        mem.put(5, BigInteger.valueOf(5));
        mem.put(6, BigInteger.valueOf(8));
        mem.put(7, BigInteger.valueOf(13));
        mem.put(8, BigInteger.valueOf(21));
        mem.put(9, BigInteger.valueOf(34));
        mem.put(10, BigInteger.valueOf(55));
    }

    BigInteger foo(int n){
        if(mem.get(n) != null){
            return mem.get(n);
        }else if(n % 2 == 0){
            mem.put(n, foo(n/2).multiply(foo(n/2 +1).multiply(BigInteger.valueOf(2)).subtract(foo(n/2))));
            return mem.get(n);
        }else{
            mem.put(n, foo(n/2 +1).pow(2).add(foo(n/2).pow(2)));
            return mem.get(n);
        }
    }

    String toString(int n){
        return foo(n).toString();
    }

    public static void main(String[] args) {
        Fibo pp = new Fibo();
        System.out.println(pp.mem.size());
        System.out.println(pp.toString(99999).length());
    }
}
