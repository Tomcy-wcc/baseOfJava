package offer.offer11_40;

import java.util.Stack;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class offer23 {

    public static void main(String[] args) {
        int[] sequence = {4, 7, 6, 5};
        boolean b = VerifySquenceOfBST(sequence);
        System.out.println(b);
    }

    public static boolean VerifySquenceOfBST(int[] sequence) {
        Stack<Integer> stack = new Stack<>();

        //先判断这个序列是否满足基本的二叉搜索数的规则
        //最后一个是根节点
        //先找到第一个比根节点小的数的位置
        int i;
        for (i = sequence.length - 2; i >= 0; i--) {
            if (sequence[i] < sequence[sequence.length - 1])
                break;
        }
        //如果前面右比根节点大的结点，则不满足后序遍历
        for (int j = 0; j <= i; j++) {
            if (sequence[j] > sequence[sequence.length - 1]) {
                return false;
            }
        }
        //假如遇到一个比栈中小的就弹栈（这个弹出栈的元素一定是在这个元素的右边），否则压栈
        if (sequence.length != 0)
            stack.push(sequence[0]);
        else return false;

        for (i = 1; i < sequence.length; i++) {
            while (!stack.isEmpty() && sequence[i] < stack.peek()) {
                stack.pop();
            }
            stack.push(sequence[i]);
            System.out.println(stack);
        }
        //看一下是否有序
        if (stack.isEmpty()) {
            return true;
        }
        int top = stack.pop();
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current > top) {
                return false;
            }
            top = current;
        }
        return true;
    }
}
