package offer.offer11_40;

/**
 * 二叉树的镜像
 *
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 */
public class offer18 {
    public void Mirror(TreeNode root) {
        if(root != null){
            //交换左子树与右子树
            TreeNode t = root.left;
            root.left = root.right;
            root.right = t;
            Mirror(root.left);
            Mirror(root.right);
        }
    }
}
