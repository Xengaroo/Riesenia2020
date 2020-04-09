import java.util.ArrayList;
import java.util.List;

public class KSucin {

	public static <E> List<List<E>> sucin(List<List<E>> lists) {
		List<List<E>> l = new ArrayList<>();
		List<List<E>> result;

		if(lists == null || lists.size() == 0) return l;

		for(E e: lists.get(0)) {
			List<E> el = new ArrayList<>();
			el.add(e);
			l.add(el);
		}

		for(int i = 1; i < lists.size(); i++) {
			result = new ArrayList<>();

			for(List<E> e1: l) {
				for(E e2: lists.get(i)) {
					List<E> r = new ArrayList<>();
					r.addAll(e1);
					r.add(e2);
					result.add(r);
				}
			}

			l = result;
		}

		return l;  // dodefinuj
	}	
	public static void main(String[] args) {
		{
			List<Integer> al1 = new ArrayList<>(); al1.add(1); al1.add(2); al1.add(3);
			List<Integer> al2 = new ArrayList<>(); al2.add(4); al2.add(5); al2.add(6);
			List<Integer> al3 = new ArrayList<>(); al3.add(10); al3.add(11);
			
			List<List<Integer>> ll = new ArrayList<>();
			ll.add(al1); ll.add(al2); ll.add(al3);
			
			System.out.print("sucin(" + ll + ")=");
			List<List<Integer>> res = sucin(ll);
			System.out.println(res + ", " + res.size());
		}
	}
}
