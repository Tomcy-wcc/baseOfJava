package offer.offer11_40;

import java.util.Stack;

/**
 * 包含min函数的栈
 *
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数
 * （时间复杂度应为O（1））。
 */
public class offer20 {

    public static void main(String[] args) {
        offer20 offer20 = new offer20();
        offer20.push(3);
        System.out.println(offer20.min());
        offer20.push(4);
        System.out.println(offer20.min());
    }

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public void push(int node) {
        stack1.push(node);
        if(!min.isEmpty()){
            Stack<Integer> temp = new Stack<>();
            if(node <= min.peek()){
                min.push(node);
            }else{
                //把该元素放到栈中适合的位置
                while(!min.isEmpty() && node > min.peek()){
                    temp.push(min.pop());
                }
                min.push(node);
                System.out.println(min);
                while (!temp.isEmpty()){
                    min.push(temp.pop());
                }
            }
        }else{
            min.push(node);
        }

    }

    public void pop() {
        if(!stack1.isEmpty()) {
            Integer pop = stack1.pop();
            //弹出相等的元素，并调整栈的结构
            Stack<Integer> temp = new Stack<>();
            while (!min.isEmpty() && min.peek() != pop){
                temp.push(min.pop());
            }
            min.pop();
            while (!temp.isEmpty()){
                min.push(temp.pop());
            }
        }
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        if(!min.isEmpty())
            return min.peek();
        else
            return Integer.MAX_VALUE;
    }
}
