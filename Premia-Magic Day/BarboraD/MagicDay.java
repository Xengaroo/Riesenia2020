import java.text.SimpleDateFormat;
import java.util.*;

public class MagicDay {
    public static int numberOfDays(int century) {
        int r = century - 1;                //palindrom - prve dve cislice roku == den (am) obratene
        int den = (r % 10) * 10 + r / 10;   //den v americkom zapise == mesiac v britskom
        if (den > 12 || den < 1) {                     //mesiac == den, nemoze byt viac ako 12
            return 0;
        }
        int rok = r * 100 + r;
        int mes = den;
        return 1;
    }

    public static void main(String[] args) {
        System.out.println(numberOfDays(1));
    }
}