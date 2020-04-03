package Vyrezane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bar<E extends Comparable<E>> extends Mobile<E> {
	private int leftArm, rightArm;
	private Mobile<E> left, right;

	public Bar(int leftArm, int rightArm, Mobile<E> left, Mobile<E> right) {
		this.leftArm = leftArm;
		this.rightArm = rightArm;
		this.left = left;
		this.right = right;
	}
	@Override
	public String toString() {
		return "[" + left + ":" + leftArm  + "-" + rightArm  + ":" + right  + "]";
	}

	@Override
	int weight() {
		return left.weight() + right.weight();
	}

	@Override
	double width() {
		return widthLeft() + widthRight();
	}

	@Override
	double widthLeft() {
		return left.widthLeft() + leftArm;
	}

	@Override
	double widthRight() {
		return right.widthRight() + rightArm;
	}

	@Override
	int height() {
		return 1 + Math.max(left.height(), right.height());
	}

	@Override
	boolean balanced() {
		return (leftArm * left.weight() == rightArm * right.weight() && left.balanced() && right.balanced()) ? true : false;
	}

	@Override
	boolean correct() {
		return (right.widthLeft() + left.widthRight() < leftArm + rightArm && left.correct() && right.correct()) ? true : false;
	}

	@Override
	Set<List<E>> words() {
		Set<List<E>> set = new HashSet<>();
//		List<E> l = new ArrayList<>();

		if(correct()) {
			for(List<E> w1: left.words()) {
				for(List<E> w2: right.words()) {
					List<E> l = new ArrayList<>();
					l.addAll(w1);
					l.addAll(w2);
					set.add(l);
				}
			}
		}
		Mobile<E> temp = left;
		left = right;
		right = temp;

		if(correct()) {
			for(List<E> w1: left.words()) {
				for(List<E> w2: right.words()) {
					List<E> l = new ArrayList<>();
					l.addAll(w1);
					l.addAll(w2);
					set.add(l);
				}
			}
		}

		temp = left;
		left = right;
		right = temp;

		return set;
	}
}
