//@https://en.wikipedia.org/wiki/Negative_base#Java
public class Nego {
    public static String toNego(int i){
        String result = "";
        int number = i;
        while (number != 0) {
            int j = number % -2;
            number /= -2;
            if (j < 0) {
                j += 2;
                number++;
            }

            result = j + result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(toNego(2));
    }
}
