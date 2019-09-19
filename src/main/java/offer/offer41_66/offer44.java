package offer.offer41_66;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * 滑动窗口的最大值
 *
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
 * {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 *
 * 滑动窗口算法
 * 借助一个队列来维持有序
 */
public class offer44 {

    public static void main(String[] args) {
        offer44 offer44 = new offer44();
        int[] num = {10,14,12,11};
        //int[] num = {1, 3, -1, -3, 5, 3, 6, 7};
        int size = 1;
        ArrayList<Integer> list = offer44.maxInWindows(num, size);
        System.out.println(list);
    }

    class Node{
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", index=" + index +
                    '}';
        }
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        //结果
        ArrayList<Integer> list = new ArrayList<>();
        if(size == 0){
            return list;
        }
        //用于维持有序队列
        LinkedList<Node> queue = new LinkedList<>();
        //遍历数组
        for(int i = 0; i < num.length; i++){
            //如果当前元素比队列头元素大，就一直做弹栈操作
            while (!queue.isEmpty() && queue.getLast().value < num[i]){
                queue.removeLast();
            }
            //从第size-1个元素开始处理
            if(i >= size-1){
                //不在滑动窗口
                if(!queue.isEmpty()&&queue.getFirst().index <= i-size){
                    //出队
                    queue.poll();
                }
                queue.offer(new Node(num[i], i));
                //最大元素还在滑动窗口
                list.add(queue.getFirst().value);
            } else{
                //前size-1个元素直接入队
                queue.offer(new Node(num[i], i));
            }

            System.out.println(queue);
        }

        return list;

    }
}
