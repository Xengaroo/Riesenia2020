import java.util.LinkedList;
import java.util.List;

public class KSucin {
    public static <E> List<List<E>> sucin(List<List<E>> lists){
        return rek(lists, 0, new LinkedList<>(), new LinkedList<>());
    }
    private static <E> List<List<E>> rek(List<List<E>> lists, int pos,List<E> actual, List<List<E>> res){
        if(pos > lists.size() - 1) {
            res.add(actual);
            return res;
        }
        for(int i = 0; i < lists.get(pos).size(); i++){
            List<E> pom = new LinkedList<>(actual);
            pom.add(lists.get(pos).get(i));
            res = rek(lists, pos + 1, pom, res);
        }
        return res;
    }
}
