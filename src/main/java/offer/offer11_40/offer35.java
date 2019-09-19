package offer.offer11_40;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 把二叉树打印成多行
 *
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 */
public class offer35 {

    public static void main(String[] args) {
        offer35 offer35 = new offer35();
        int[] arr = {54, 88, 44, 25};
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : arr){
            list.add(i);
        }
        BinarySearchTree binaryTree = new BinarySearchTree(list);
        ArrayList<ArrayList<Integer>> print = offer35.Print(binaryTree.getRoot());
        for(ArrayList<Integer> arrayList : print){
            System.out.println(arrayList);
        }
    }

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot == null) return result;
        LinkedList<TreeNode> parentQueue = new LinkedList<>();
        LinkedList<TreeNode> childQueue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        parentQueue.offer(pRoot);
        while (!parentQueue.isEmpty()){
            TreeNode treeNode = parentQueue.poll();
            list.add(treeNode.val);
            if(treeNode.left != null){
                childQueue.offer(treeNode.left);
            }
            if(treeNode.right != null){
                childQueue.offer(treeNode.right);
            }
            if(parentQueue.isEmpty()){
                parentQueue = childQueue;
                childQueue = new LinkedList<>();
                result.add(list);
                list = new ArrayList<>();
            }
        }
        return result;
    }
}
