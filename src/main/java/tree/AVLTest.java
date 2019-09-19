package tree;

public class AVLTest {
	
	public static void main(String[] args) {
		Integer[] objs = new Integer[20];
		for(int i = 0; i < objs.length; i++)
			objs[i] = i+1;
		AVL<Integer> avl = new AVL<>(objs);
		avl.delete(4);
		avl.inorder();
	}

}
