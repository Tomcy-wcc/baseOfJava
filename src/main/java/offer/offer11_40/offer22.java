package offer.offer11_40;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * 从上往下打印二叉树
 *
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * 二叉树的层次遍历，借助一个队列
 */
public class offer22 {

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> vals = new ArrayList<>();
        if(root == null){
            return vals;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            vals.add(node.val);
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return vals;
    }

    public static void main(String[] args) {

    }

}
