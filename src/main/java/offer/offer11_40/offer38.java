package offer.offer11_40;

import java.util.ArrayList;

/**
 * 平衡二叉树
 *
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class offer38 {

    public static void main(String[] args) {
        offer38 offer38 = new offer38();
        Integer[] arr = {46, 36, 92, 10, 80, 44, 54, 4, 63, 39};
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : arr){
            list.add(i);
        }
        BinarySearchTree binaryTree = new BinarySearchTree(list);
        //binaryTree.inOrder();
        boolean b = offer38.IsBalanced_Solution(binaryTree.getRoot());
        System.out.println(b);
    }

    /**
     * 计算结点的左子树高度和右子树高度
     * 如果高度的绝对值大于1就不平衡
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root!=null){
            return IsBalanced(root);
        }else{
            return true;
        }
    }



    public boolean IsBalanced(TreeNode root){
        if (root != null){
            //左子树高度
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            return Math.abs(leftHeight-rightHeight) <= 1;
        }
        return true;
    }

    /**
     * 计算结点的高度
     * @param root
     * @return
     */
    public int getHeight(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }
}
