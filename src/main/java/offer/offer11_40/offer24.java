package offer.offer11_40;

import java.util.*;

/**
 * 二叉树中和为某一值的路径
 * <p>
 * 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * (注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class offer24 {

    public static void main(String[] args) {
        offer24 offer24 = new offer24();
        int[] a = {10,5,12,4,7};
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : a){
            list.add(i);
        }
        BinarySearchTree binaryTree = new BinarySearchTree(list);
        TreeNode root = binaryTree.getRoot();
        ArrayList<ArrayList<Integer>> arrayLists = offer24.FindPath(root, 22);
        System.out.println(arrayLists);
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        path(result, root, target, new ArrayList<>());
        return result;
    }

    public void path(ArrayList<ArrayList<Integer>> result, TreeNode root, int target, ArrayList<Integer> parent) {
        if (root != null) {
            parent.add(root.val);
            target -= root.val;
            if(root.left != null && target > 0){
                path(result, root.left, target, (ArrayList<Integer>) parent.clone());
            }
            if(root.right != null && target > 0){
                path(result, root.right, target, (ArrayList<Integer>) parent.clone());
            }
            if(root.right == null && root.left == null && 0 == target){
                result.add(parent);
            }
        }
    }

}
