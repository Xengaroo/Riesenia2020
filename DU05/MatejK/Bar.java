//package Vyrezane;
import java.util.*;

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
		return Math.max(leftSideLength(), rightSideLength());
	}

	@Override
	double leftSideLength() {
		return leftArm + left.leftSideLength();
	} //dlzka lavych stran palic

	@Override
	double rightSideLength() {
		return rightArm + right.rightSideLength();
	}

	@Override
	int height() {
		return 1 + Math.max(right.height(), left.height());
	}

	@Override
	boolean balanced() {
		return left.weight() * leftArm == right.weight() * rightArm;
	}

	@Override
	boolean correct() {
		int dlzkaTohto = leftArm + rightArm;
		if(Math.max(left.rightSideLength(),left.leftSideLength()) + Math.max(right.leftSideLength(),right.rightSideLength()) <= dlzkaTohto){
			return true;
		}
		return false;
	}

	@Override
	Set<List<E>> words() {
		if(correct() == false){
			return new HashSet<List<E>>();
		}
		return allWords();
	}

	@Override
	Set<List<E>> allWords() {	//aby som nemusel vzdy correct() kontrolovat
		Set<List<E>> res = new HashSet<>();
		ArrayList<E> list = new ArrayList<>();

		addWords(res, list, left, right);
		addWords(res, list, right, left);
		return res;
	}

	private void addWords(Set<List<E>> res, ArrayList<E> list, Mobile<E> right, Mobile<E> left) {
		for(List<E> word1 : right.allWords()){
			for(List<E> word2 : left.allWords()){
				list.addAll(word1);
				list.addAll(word2);
				res.add((List<E>) list.clone());
				list.clear();
			}
		}
	}

}
