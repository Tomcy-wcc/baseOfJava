package offer.offer1_10;

import offer.offer11_40.TreeNode;

/**
 * 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class offer4 {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = null;
        return createTreeBypreAndIn(root, pre, 0, pre.length-1, in, 0, in.length-1);
    }

    private TreeNode createTreeBypreAndIn(TreeNode root,int[] preOrder, int preStart, int preEnd,int[] inOrder, int inStart, int inEnd) {
        //创建结点，先序遍历的第一个便是根节点
        root = new TreeNode(preOrder[preStart]);
        //在中序中找出根的位置
        int _index = inStart;
        while (preOrder[preStart] != inOrder[_index]) {
            _index++;
        }
        //假如 _index = inStart 说明没有左结点
        if (_index == inStart) {
            root.left = null;
        } else {
            /**
             * 递归建立左子树, 则左子树的建树空间变为:
             *      preStart = preStart+1
             *      preEnd = preStart + _index -1
             *      inStart = inStart;
             *      inEnd = _index-1
             */
            root.left = createTreeBypreAndIn(root.left, preOrder, preStart + 1, preStart + _index - 1, inOrder, inStart, _index - 1);
        }
        //假如 _index = inEnd 说明没有右结点
        if (_index == inEnd) {
            root.right = null;
        } else {
            /**
             * 递归建立右子树, 则右子树的建树空间变为:
             * preStart = preStart + _index - inStart + 1(_index - inStart + 1代表左子树有多少个结点)
             * preEnd = preEnd
             * inStart = _index + 1
             * inEnd = inEnd
             */
            root.right = createTreeBypreAndIn(root.right, preOrder, preStart + _index - inStart + 1,  preEnd,inOrder, _index + 1, inEnd);
        }

        return root;
    }
}
