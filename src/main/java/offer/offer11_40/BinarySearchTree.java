package offer.offer11_40;

import java.util.ArrayList;

/**
 * 二叉树
 */
public class BinarySearchTree {

    private TreeNode root;

    public BinarySearchTree(ArrayList<Integer> vals) {
        for(Integer val : vals){
            insert(val);
        }
    }

    public void insert(int val){
        root = insert(root, val);
    }

    public TreeNode insert(TreeNode root, int val){
        if(root == null){
            root = new TreeNode(val);
        }else {
            if(val > root.val){
                root.right = insert(root.right, val);
            }else if(val <= root.val){
                root.left = insert(root.left, val);
            }
        }
        return root;
    }

    public void preOrder(){
        preOrder(root);
    }

    public void preOrder(TreeNode root){
        if(root!=null){
            System.out.println(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    public void inOrder(TreeNode root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
