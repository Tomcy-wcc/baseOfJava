package offer.offer11_40;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中只出现一次的数字
 *
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。
 */
public class offer37 {

    public static void main(String[] args) {
        offer37 offer37 = new offer37();
        int[] array = {2, 2, 3, 3, 1, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        offer37.FindNumsAppearOnce(array, num1, num2);
        System.out.println(num1[0]+"-->"+num2[0]);
    }

    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : array){
            if(!map.containsKey(i)){
                map.put(i, 1);
            }else {
                map.put(i, map.get(i)+1);
            }
        }
        int[] arr = new int[2];
        int i = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() == 1){
                arr[i++] = entry.getKey();
            }
        }
        num1[0] = arr[0];
        num2[0] = arr[1];
    }

}
