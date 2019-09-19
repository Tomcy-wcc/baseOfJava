package offer.offer41_66;

import offer.offer11_40.BinarySearchTree;
import offer.offer11_40.TreeNode;

import java.util.ArrayList;

/**
 * 二叉搜索树的第k个结点
 *
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。
 * 例如，（5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 */
public class offer45 {

    public static void main(String[] args) {
        offer45 offer45 = new offer45();
        Integer[] arr = {8,6,10,5,7,9,11};
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : arr){
            list.add(i);
        }
        BinarySearchTree binaryTree = new BinarySearchTree(list);
        TreeNode treeNode = offer45.KthNode(binaryTree.getRoot(), 1);
        System.out.println(treeNode);
    }

    public TreeNode KthNode(TreeNode pRoot, int k) {
        if(k == 0){
            return null;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        KthNode(pRoot, list, k);
        if(list.size() < k){
            return null;
        }
        return list.get(k-1);
    }

    public void KthNode(TreeNode pRoot, ArrayList<TreeNode> list, int k) {
        if(pRoot != null){
            KthNode(pRoot.left, list, k);
            if(list.size() <= k){
                list.add(pRoot);
                System.out.println(list);
            }else {
                return;
            }
            KthNode(pRoot.right, list, k);
        }
    }
}
