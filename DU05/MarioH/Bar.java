//package Vyrezane;

import java.util.*;

public class Bar<E extends Comparable<E>> extends Mobile<E> {
	private int leftArm, rightArm;
	private Mobile<E> left, right;
	private static Bar root;

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
		return (rightArm + right.width() > leftArm + left.width()) ? rightArm + right.width() : leftArm + left.width();
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
		List<Mobile<E>> l = bars();
		Set<List<E>> set = new HashSet<>();

		if(correct())
		for(int i = 0; i < Math.pow(2, l.size()); i++) {
			for(int j = 0; j < l.size(); j++) {
				if(((i>>j) & 1) == 1) l.get(j).swap();
			}

//			if(correct()) {
				set.add(getElements());
//			}

			for(int j = 0; j < l.size(); j++) {
				if(((i>>j) & 1) == 1) l.get(j).swap();
			}
		}


		return set;
	}

	@Override
	List<Mobile<E>> bars() {
		List<Mobile<E>> l = new ArrayList<>();
		l.add(this);

		List<Mobile<E>> ll = left.bars();
		if(ll != null) l.addAll(left.bars());

		List<Mobile<E>> rl = right.bars();
		if(rl != null) l.addAll(right.bars());

		return l;
	}

	@Override
	void swap() {
		Mobile<E> temp = left;
		left = right;
		right = temp;

		int tempArm = leftArm;
		leftArm = rightArm;
		rightArm = tempArm;
	}

	@Override
	List<E> getElements() {
		List<E> l = new ArrayList<>();
		l.addAll(left.getElements());
		l.addAll(right.getElements());

		return l;
	}
}
