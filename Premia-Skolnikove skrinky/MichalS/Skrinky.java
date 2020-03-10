public class Skrinky {
    public static long skrinky(long n) {
        long i = (long)Math.floor(Math.sqrt(n));
        return (i * (i + 1) * (2 * i + 1)) / 6;
    }
}
