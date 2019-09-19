package offer.offer41_66;

import offer.offer11_40.BinarySearchTree;
import offer.offer11_40.BinaryTree;
import offer.offer11_40.TreeNode;
import tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 序列化二叉树
 *
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 *
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
 * 从而使得内存中建立起来的二叉树可以持久保存。
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
 * 序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），
 * 以 ！ 表示一个结点值的结束（value!）。
 *
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class offer47 {

    public static void main(String[] args) {
        offer47 offer47 = new offer47();
        String str = "5!4!#!3!#!2!#!#!#!";
        TreeNode deserialize = offer47.Deserialize(str);
        System.out.println(deserialize);
        String serialize = offer47.Serialize(deserialize);
        System.out.println(serialize);
    }

    String Serialize(TreeNode root) {
        //层次遍历进行序列化
        //保存遍历结果
        ArrayList<TreeNode> list = new ArrayList<>();
        //辅助队列
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            list.add(node);
            if(node != null){
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(TreeNode treeNode : list){
            if(treeNode == null){
                stringBuilder.append('#').append('!');
            }else {
                stringBuilder.append(treeNode.val).append('!');
            }
        }
        return stringBuilder.toString();
    }

    TreeNode Deserialize(String str) {
        String[] split = str.split("!");
        ArrayList<TreeNode> list = new ArrayList<>();
        for(int i = 0; i < split.length; i++){
            if(split[i].equals("#")){
                list.add(null);
            }else{
                list.add(new TreeNode(Integer.valueOf(split[i])));
            }
        }
        System.out.println(list);

        //5!4!#!3!#!2!#!#!#!
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = list.remove(0);
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!list.isEmpty() && node != null){
                node.left = list.remove(0);
                queue.offer(node.left);
                node.right = list.remove(0);
                queue.offer(node.right);
            }
        }

        return root;
    }
}
