package offer.offer11_40;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 按之字形顺序打印二叉树
 * <p>
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
 * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 */
public class offer36 {

    public static void main(String[] args) {
        offer36 offer36 = new offer36();
        int[] arr = {8, 6, 10, 5, 7, 9, 11};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        BinarySearchTree binaryTree = new BinarySearchTree(list);
        ArrayList<ArrayList<Integer>> print = offer36.Print(binaryTree.getRoot());
        for (ArrayList<Integer> arrayList : print) {
            System.out.println(arrayList);
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (pRoot == null) return result;
        LinkedList<TreeNode> parentQueue = new LinkedList<>();
        LinkedList<TreeNode> childQueue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        parentQueue.offer(pRoot);
        boolean flag = true;//true从左到右  false从右到左
        while (!parentQueue.isEmpty()) {
            TreeNode treeNode = parentQueue.poll();
            list.add(treeNode.val);

            if (treeNode.left != null) {
                childQueue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                childQueue.offer(treeNode.right);
            }

            if (parentQueue.isEmpty()) {
                parentQueue = childQueue;
                childQueue = new LinkedList<>();
                if(flag){
                    result.add(list);
                }else {
                    Collections.reverse(list);
                    result.add(list);
                }
                list = new ArrayList<>();
                flag = !flag;
            }
        }
        return result;
    }

}
