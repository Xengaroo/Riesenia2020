import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Sucty {

    public static List<Integer> sucty(List<Integer> as, List<Integer> bs){
        Set<Integer> res = new HashSet<>();
        as.stream().forEach(x -> {bs.stream().forEach(y -> res.add(x+y));});
        return new LinkedList<>(res);
    }
    public static List<Integer> sucty(List<List<Integer>> ass){
        Set<Integer> res = new HashSet<>();
        if(ass.size() > 1){
            List<List<Integer>> a = ass.subList(1,ass.size());
            List<Integer> s = sucty(a);
            ass.get(0).stream().forEach(x -> {s.stream().forEach(y -> {res.add(x + y);});});
        }else{
            res.addAll(ass.get(0));
        }
        return new LinkedList<>(res);
    }

    public static void main(String[] args) {
//        System.out.println(sucty(List.of(1,2,3,4,5), List.of(3,4,5,6,7)));
        System.out.println(sucty(List.of(List.of(1,2,3,4,5), List.of(1,2,3,4,5), List.of(1,2,3,4,5))));
    }
}
