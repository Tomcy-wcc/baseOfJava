package offer.offer11_40;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryTree {
	
	TreeNode root;

	public BinaryTree(ArrayList<Integer> datas) {
		createTree(datas);
	}

	//1,2,3,#,5
	TreeNode createTree(ArrayList<Integer> datas) {
		LinkedList<TreeNode> queue = new LinkedList<>();
		if(datas.get(0) == null) {
			root = null;
		}else {
			Integer data = datas.remove(0);
			root = new TreeNode(data);
			queue.offer(root);
			while(!datas.isEmpty()) {
				TreeNode node = queue.pollFirst();
				data = datas.remove(0);
				if(data != null) {
					node.left = new TreeNode(data);
				}else {
					node.left = null;
				}
				queue.addLast(node.left);
				data = datas.remove(0);
				if(data != null) {
					node.right = new TreeNode(data);
				}else {
					node.right = null;
				}
				queue.addLast(node.right);
			}
		}
		return root;
	}
	
	
	
	public void preOrder() {
		System.out.println(root);
        preOrderVal(root);
    }

    /**
     * 先序遍历
     *
     * @param root
     */
    private void preOrderVal(TreeNode root) {
        if (root != null) { 
        	System.out.println(root.val);
            preOrderVal(root.left);
            preOrderVal(root.right);
        }
    }

	public TreeNode getRoot() {
		return root;
	}
}
