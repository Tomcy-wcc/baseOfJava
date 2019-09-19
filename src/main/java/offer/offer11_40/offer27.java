package offer.offer11_40;

import java.util.ArrayList;

/**
 * 最小的K个数
 *
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class offer27 {
    public static void main(String[] args) {
        int[] input = {4,5,1,6,2,7,3,8};
        offer27 offer27  = new offer27();
        ArrayList<Integer> list = offer27.GetLeastNumbers_Solution(input, 1);
        System.out.println(list);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(k > input.length){
            return list;
        }
        quick(input, 0, input.length-1, 4);
        for(int i = 0; i < k; i++){
            list.add(input[i]);
        }
        return list;
    }

    public void quick(int [] input, int left, int right, int k){
        if(left < right){
            int p = portion(input, left, right, k);
            if(p == k){
                return;
            }
            quick(input, left, p-1, k);
            quick(input, p+1, right, k);
        }
    }

    //进行快排
    public int portion(int [] input, int left, int right, int k){
        int i = left+1;
        int j = right;
        while (i < j){
            //找大的
            while (i < j && input[i] < input[left]){
                i++;
            }
            //找小的
            while (i < j  && input[j] > input[left]){
                j--;
            }
            System.out.println(i+"--->"+j);
            swap(input, i, j);
        }
        //得到基准位
        int p = i-1;
        swap(input, p, left);
        return p;
    }

    //交换

    public void swap(int[] input, int i, int j){
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
