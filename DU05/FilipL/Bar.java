import java.lang.reflect.Array;
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
		if (left instanceof Bar && right instanceof Bar) {
			double width_l = ((Bar<E>) left).width() + leftArm;
			double width_r = ((Bar<E>) right).width() + rightArm;
			return Math.max(width_l, width_r);
		} else if (left instanceof Bar) {
			return Math.max(rightArm, ((Bar<E>) left).width() + leftArm);
		} else if (right instanceof Bar) {
			return Math.max(leftArm, ((Bar<E>) right).width() + rightArm);

		} else return Math.max((double) leftArm, (double) rightArm);
	}

	@Override
	int height() {
		return 1 + Math.max(left.height(), right.height());
	}

	@Override
	boolean balanced() {
		return leftArm * left.weight() == rightArm * right.weight();
	}

	@Override
	boolean correct() {
		if (left instanceof Bar && right instanceof Bar) {
			return (((Bar<E>) left).rightArm + ((Bar<E>) right).leftArm < leftArm + rightArm) && left.correct() && right.correct();
		} else if (left instanceof Bar) {
			return ((Bar<E>) left).rightArm < leftArm + rightArm && left.correct();
		} else if (right instanceof Bar) {
			return ((Bar<E>) right).leftArm < leftArm + rightArm && right.correct();
		} else return true;
	}

	@Override
	Set<List<E>> words() {
		if (! correct()) return new HashSet<>();

		Set<List<E>> s = new HashSet<>();
		for (List<E> pole : left.words()) {
			List<E> result = new ArrayList<>(pole);
			for (List<E> pole2 : right.words()) {
				result.addAll(pole2);
				s.add(result);
				result = new ArrayList<>(pole);
			}
		}

		for (List<E> pole : right.words()) {
			List<E> result = new ArrayList<>(pole);
			for (List<E> pole2 : left.words()) {
				result.addAll(pole2);
				s.add(result);
				result = new ArrayList<>(pole);
			}
		}

		return s;

	}

}
