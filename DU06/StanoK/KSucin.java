import java.util.*;
import java.util.stream.Stream;

public class KSucin {

	public static <E> List<List<E>> sucin(List<List<E>> lists) {
		//Stream<List<E>> str1 = lists.stream();
		List<List<E>> vysl = new ArrayList<>();
		for(List<E> list1 : lists) {
			if(vysl.size() == 0) {
				for(E elem: list1) {
					List<E> temp = new ArrayList<>();
					temp.add(elem);
					vysl.add(temp);
				}
				continue;
			}
			List<List<E>> novy_vysl = new ArrayList<>();
			for(List<E> list1_vysl : vysl) {
				for(E elem : list1) {
					List<E> temp = new ArrayList<>();
					temp.addAll(list1_vysl);
					temp.add(elem);
					novy_vysl.add(temp);
				}
			}
			vysl = novy_vysl;
		}
		return vysl;
	}	
	public static void main(String[] args) {
		{
			List<Integer> al1 = new ArrayList<>(); al1.add(1); al1.add(2); al1.add(3);
			List<Integer> al2 = new ArrayList<>(); al2.add(4); al2.add(5); al2.add(6);
			List<Integer> al3 = new ArrayList<>(); al3.add(10); al3.add(11);
//			List<Integer> al1 = new ArrayList<>(); al1.add(1);
//			List<Integer> al2 = new ArrayList<>(); al2.add(4);
//			List<Integer> al3 = new ArrayList<>(); al3.add(10);
			
			List<List<Integer>> ll = new ArrayList<>();
			ll.add(al1); ll.add(al2); ll.add(al3);
			
			System.out.print("sucin(" + ll + ")=");
			List<List<Integer>> res = sucin(ll);
			System.out.println(res + ", " + res.size());
		}
	}
}
