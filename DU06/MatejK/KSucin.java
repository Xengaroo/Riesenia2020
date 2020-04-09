import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KSucin {

	public static <E> List<List<E>> sucin(List<List<E>> lists) {
		return cartesianProduct(0, lists);
	}

	private static <E> List<List<E>> cartesianProduct(int index, List<List<E>> lists) {
		List<List<E>> res = new ArrayList<List<E>>();
		if (index == lists.size()) {
			res.add(new ArrayList<E>());
		}
		else {
			for (E prvok : lists.get(index)) {
				for (List<E> list1 : cartesianProduct(index+1, lists)) {
					list1.add(prvok);
					res.add(list1);
				}
			}
		}
		return res;
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

//https://stackoverflow.com/questions/714108/cartesian-product-of-arbitrary-sets-in-java