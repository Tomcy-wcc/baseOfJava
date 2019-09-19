package offer.offer41_66;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 二叉树的下一个结点
 *
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class offer48 {
    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;
        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        //1、pNode右结点都为空
        if(pNode.right == null){
            //根节点
            if(pNode.next == null){
                return null;
            }
            if(pNode == pNode.next.left){
                return pNode.next;
            } else{//最右边那个结点
                TreeLinkNode pNodeParent = pNode.next;
                TreeLinkNode current = pNode;
                while (pNodeParent!= null && current == pNodeParent.right){
                    current = pNodeParent;
                    pNodeParent = pNodeParent.next;
                }
                //到了根节点，说明pNode已经是最后一个了
                if(pNodeParent == null){
                    return null;
                }
                return pNode.next.next;
            }

        }
        //2、pNode右结点不为空，返回右结点最左边的结点
        else{
            TreeLinkNode rightChild = pNode.right;
            while (rightChild.left != null){
                rightChild = rightChild.left;
            }
            return rightChild;
        }
    }
}
