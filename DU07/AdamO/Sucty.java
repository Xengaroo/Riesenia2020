import java.util.*;
import java.util.stream.*;


public class Sucty {

    public static List<Integer> sucty(List<Integer> as, List<Integer> bs) {
        return new ArrayList<Integer>(as.stream().map(a -> bs.stream().map(b -> a + b)).flatMap(x -> x).collect(Collectors.toSet()));
    }

    public static List<Integer> sucty(List<List<Integer>> ass) {
            List<List<Integer>> ass2 = new ArrayList<>(ass);
            List<Integer> vyhodeny = ass2.remove(ass2.size() - 1);
            return ass.size() <= 1 ? ass.stream().map(Collection::stream).flatMap(x -> x).collect(Collectors.toList()) : sucty(sucty(ass2), vyhodeny);
    }

}
