package offer.offer11_40;

import java.util.HashMap;

/**
 * 数组中出现次数超过一半的数字
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class offer26 {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 1){
            return 1;
        }
        HashMap<Integer, Integer> result = new HashMap<>();
        for(int i : array){
            if(result.containsKey(i)){
                result.put(i, result.get(i)+1);
                if(result.get(i) > array.length /2){
                    return i;
                }
            }else{
                result.put(i, 1);
            }
        }
        return 0;
    }
}
