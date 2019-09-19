package tree;

public class BSTTest {

	public static void main(String[] args) {
		Integer[] objs = { 1, 3, 6, 7, 9, 4, 10, 5, 2, 8 };
		BST<Integer> bst = new BST<>(objs);
		bst.inorder();
	}
}
