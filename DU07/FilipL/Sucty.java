import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sucty {

    public static List<Integer> sucet(Integer a, List<Integer> bs) {
        List<Integer> res = bs.stream().map(integer -> integer + a).collect(Collectors.toList());
        return res;
    }

    public static List<Integer> sucty(List<Integer> as, List<Integer> bs) {
        Stream<Integer> str = as.stream().map(integer -> sucet(integer, bs)).flatMap(Collection::stream).distinct().sorted();
        List<Integer> res = str.collect(Collectors.toList());
        return res;
    }

    public static List<Integer> sucty(List<List<Integer>> ass) {
        if (ass.size() == 1) return ass.stream().flatMap(Collection::stream).collect(Collectors.toList());
        if (ass.size() == 2) {
            return sucty(ass.get(0), ass.get(1));
        }
        else {
            return sucty(ass.get(0), sucty(ass.subList(1, ass.size())));
        }
    }
}
