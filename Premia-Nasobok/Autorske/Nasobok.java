import java.util.ArrayList;
import java.util.List;

public class Nasobok {
    public static String nasobok10(int n) {
        if (n == 1) return "1";
        int count = 0;
        int rest = 0;
        int index = 0;
        List<Integer> rests = new ArrayList<>();
        for(;;) {
            count++;
            rest = (10*rest+1) % n;
            if (rest == 0)
                break;
            index = rests.indexOf(rest);
            if (index >= 0) break;
            rests.add(rest);
        }
        StringBuilder sb = new StringBuilder();
        while(count-->0) {
           sb.append((count > index)?"1":"0");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        for(int i = 1; i < 100; i++)
            System.out.println(i + " | " + nasobok10(i));
    }
}
