//package Vyrezane;
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
	int height() {
		return 0;
	}

	@Override
	boolean balanced() {
		return false;
	}

	@Override
	boolean correct() {
		return false;
	}

	@Override
	Set<List<E>> words() {
		return allWords();
	}

	@Override
	Set<List<E>> allWords() {
		Set<List<E>> set = new HashSet<>();
		List<E> list = new ArrayList<>(List.of(sign));
		set.add(list);
		return set;
	}

	@Override
	double leftSideLength() {
		return 0;
	}

	@Override
	double rightSideLength() {
		return 0;
	}




}
