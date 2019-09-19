package offer.offer11_40;

import java.util.ArrayList;

/**
 * 把数组排成最小的数
 *
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class offer30_not {

    public static void main(String[] args) {
        offer30_not offer30 = new offer30_not();
        String s = offer30.PrintMinNumber(new int[]{3,32,321});
        System.out.println(s);
    }


    public String PrintMinNumber(int [] numbers) {
        return null;
    }
    /**
     * 将n用数组表示
     * @param n
     * @return
     */
    private ArrayList<Integer> num2Arr(int n){
        ArrayList<Integer> arr = new ArrayList<>();
        int p = 1;
        while (n != 0){
            arr.add(n % 10);
            n /= 10;
        }
        return arr;
    }
}
