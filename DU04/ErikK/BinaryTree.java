import java.util.HashSet;
import java.util.Set;

public class BinaryTree implements BinaryTreeInterface, Iterovatelne{
	private BinaryNode root;
	private BinaryNode iter;
	private Set<BinaryNode> visited = new HashSet<>();
	private boolean wasChange;

	public BinaryTree() {
		root = null;
	}

	public BinaryTree(BinaryNode root) {
		this.root = root;
	}

	public boolean find(Long x) {
		if(root == null){
			return false;
		}
		return root.find(x);
	}

	public BinaryTree insert(Long x) {
		if(root == null){
			root = new BinaryNode(x);
		}
		else{
			root.insert(x);
		}
		return this;
	}

	public BinaryTree delete(Long key) {
		if(root == null){
			return this;
		}
		else{
			root.delete(key);
		}
		return this;
	}

	public String toString() {
		return (root == null) ? "Prazdny strom." : root.toString();
	}

	private void rek(BinaryNode n){
		if (n == null || wasChange){
			return;
		}
		rek(n.left);
		if (wasChange){
			return;
		}
		if (!visited.contains(n)) {
			if (iter == null || n.key > iter.key){
				iter = n;
				visited.add(iter);
				wasChange = true;
			} else {
				visited.add(n);
			}
			return;
		}
		rek(n.right);
	}

	@Override
	public void reset() {
		if (root == null){
			iter = new BinaryNode(Long.MAX_VALUE);
			return;
		}
		iter = null;
		visited = new HashSet<>();
	}

	@Override
	public long dalsi() {
		BinaryNode n = iter;
		wasChange = false;
		rek(root);
		if (iter.equals(n)){
			return Long.MAX_VALUE;
		}
		return iter.key;
	}

	@Override
	public long dalsiParny() {
		long ret = dalsi();
		while (ret != Long.MAX_VALUE && ret % 2 != 0){
			ret = dalsi();
		}
		return ret;
	}
}
