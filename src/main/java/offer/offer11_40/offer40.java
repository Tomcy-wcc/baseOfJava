package offer.offer11_40;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 对称的二叉树
 *
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。
 * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 */
public class offer40 {

    public static void main(String[] args) {
        offer40 offer40 = new offer40();
        Integer[] arr = {8,6,6,5,7,7,null};
        ArrayList<Integer> list = new ArrayList<>();
        for (Integer i : arr) {
            list.add(i);
        }
        BinaryTree binaryTree = new BinaryTree(list);
        boolean symmetrical = offer40.isSymmetrical(binaryTree.getRoot());
        System.out.println(symmetrical);
    }



    public boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) return true;
        LinkedList<TreeNode> parentQueue = new LinkedList<>();
        LinkedList<TreeNode> childQueue = new LinkedList<>();
        parentQueue.offer(pRoot);
        while (!parentQueue.isEmpty()){
            TreeNode treeNode = parentQueue.poll();
            if(treeNode!=null){
                childQueue.offer(treeNode.left);
                childQueue.offer(treeNode.right);
                if(parentQueue.isEmpty()){
                    parentQueue = childQueue;
                    //System.out.println(childQueue);
                    //看一下子结点是否对称
                    for(int i = 0; i < childQueue.size()/2; i++){
                        TreeNode left = childQueue.get(i);
                        TreeNode right = childQueue.get(childQueue.size()-i-1);
                        if((left != null && right == null) || (left == null && right != null) ){
                            return false;
                        }
                        if(left != null && right != null && left.val != right.val){
                            return false;
                        }
                    }
                    childQueue = new LinkedList<>();
                }
            }
        }
        return true;
    }
}
