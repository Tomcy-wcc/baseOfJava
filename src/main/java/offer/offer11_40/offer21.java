package offer.offer11_40;


import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * <p>
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 * <p>
 * 1, 2, 3, 4, 5
 * 4, 3, 5, 1, 2
 * <p>
 * <p>
 * 思路：
 * 遍历出栈序列
 * 遍历压栈序列， 找与当前出栈元素相等的，
 * 再找的过程中，假如压栈的元素不等于弹栈的元素，那么将次元素压入辅助栈中
 * 如果相等，那么退出遍历压栈序列，并记录当前已经遍历到那个位置
 * 假如压栈序列还有元素，那么直接pop,否则判断最后一个元素是否等于当前出栈元素，如果不相等，则不满足，相等的话看下一个出栈元素
 * 根据这个原理就可以判断弹栈序列是否符合弹栈的规律
 */
public class offer21 {

    public static void main(String[] args) {
        int[] pushA = {1, 2, 3, 4, 5};
        int[] popA = {4, 5, 3, 1, 2};
        System.out.println(IsPopOrder(pushA, popA));
    }
    /**
     * @param pushA 压栈序列
     * @param popA  弹栈序列
     * @return
     */
    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> temp = new Stack<>();
        int len = pushA.length;
        int count = 0;
        int j, i;
        for (j = 0; j < len; j++) {
            for (i = count; i < len; i++) {
                temp.push(pushA[i]);
                count++;
                //如果找到了，就停止（说明前面的元素在这个元素前就进了栈）
                if (popA[j] == pushA[i]) break;
            }
            //找到了相等的，压栈序列后面还有元素，直接弹出
            if(count < len){
                temp.pop();
            }else{//压栈序列后面没有元素了，已经是最后一个了
                if(temp.peek() != popA[j])break;//不相等说明不符合出栈规律
                else temp.pop();//相等就弹出
            }
        }
        //如果j == len说明出栈序列已经遍历完了，符合出栈规律
        return j == len;
    }
}
