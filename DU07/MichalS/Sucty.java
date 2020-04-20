import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Sucty {
    public static List<Integer> sucty(List<Integer> as, List<Integer> bs) {
        if (as == null || bs == null) return null;
        if (as.size() == 0) return bs;
        if (bs.size() == 0) return as;

        return as.stream().flatMap(i -> bs.stream().map(j -> i + j)).distinct().sorted().collect(Collectors.toList());
    }

    public static List<Integer> sucty(List<List<Integer>> ass) {
        Optional<List<Integer>> s = ass.stream().reduce(Sucty::sucty);
        if (s.isEmpty()) {
            return null;
        }
        return s.get();
    }
}
