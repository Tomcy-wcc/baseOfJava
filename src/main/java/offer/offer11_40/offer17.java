package offer.offer11_40;

import java.util.ArrayList;

/**
 * 树的子结构
 * <p>
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class offer17 {

    public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        //假如root1 或root2是空树，直接返回false
        if (root2 != null && root1 != null) {
            //假如根节点相同
            if (root1.val == root2.val) result = doesTree1HasTree2(root1, root2);

            //根节点不相等，看root1的左子树是否包含root2
            if (!result) result = HasSubtree(root1.left, root2);

            //root1的左子树不包含root2，看root1的右子树是否包含root2
            if (!result) result = HasSubtree(root1.right, root2);
        }
        return result;
    }

    /**
     * 判断当根节点相同，其他节点是否相同
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean doesTree1HasTree2(TreeNode root1, TreeNode root2) {
        //root2遍历完了，说明root1包含root2
        if (root2 == null) {
            return true;
        }
        //root1已经遍历完了
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        //判断左子树还有右子树是否相等
        return doesTree1HasTree2(root1.left, root2.left) && doesTree1HasTree2(root1.right, root2.right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> vals = new ArrayList<>();
        vals.add(5);
        vals.add(6);
        vals.add(4);
        vals.add(3);
        BinarySearchTree binaryTree1 = new BinarySearchTree(vals);
        binaryTree1.preOrder();

        System.out.println("----------------------------");

        ArrayList<Integer> vals2 = new ArrayList<>();
        vals2.add(5);
        vals2.add(6);
        BinarySearchTree binaryTree2 = new BinarySearchTree(vals2);
        binaryTree2.preOrder();

        System.out.println(HasSubtree(binaryTree1.getRoot(), binaryTree2.getRoot()));
    }
}
