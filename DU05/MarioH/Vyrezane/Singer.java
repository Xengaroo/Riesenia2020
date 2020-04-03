package Vyrezane;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Singer<E extends Comparable<E>> extends Mobile<E > {
	private E sign;
	private int weight;
	public Singer(E sign, int weight) {
		this.sign = sign;
		this.weight = weight;
	}	
	@Override
	public String toString() {
		return "(" + sign + "," + weight + ")";
	}

	@Override
	int weight() {
		return weight;
	}

	@Override
	double width() {
		return 0;
	}

	@Override
	double widthLeft() {
		return 0;
	}

	@Override
	double widthRight() {
		return 0;
	}

	@Override
	int height() {
		return 0;
	}

	@Override
	boolean balanced() {
		return true;
	}

	@Override
	boolean correct() {
		return true;
	}

	@Override
	Set<List<E>> words() {
		Set<List<E>> set = new HashSet<>();
		List<E> l = new ArrayList<>();
		l.add(sign);
		set.add(l);

		return set;
	}
}
