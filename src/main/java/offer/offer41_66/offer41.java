package offer.offer41_66;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数组中重复的数字
 *
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，
 * 但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class offer41 {

    public static void main(String[] args) {
        offer41 offer41 = new offer41();
        int[] numbers = {};
        int length = numbers.length;
        int[] duplication = new int[1];
        boolean b = offer41.duplicate(numbers, length, duplication);
        System.out.println(b+"---->"+duplication[0]);
    }

    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if(length == 0){
            duplication[0] = -1;
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : numbers){
            if(!map.containsKey(num)){
                map.put(num, 1);
            }else{
                duplication[0] = num;
                return true;
            }
        }
        duplication[0] = -1;
        return false;
    }
}
