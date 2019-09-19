package offer.offer41_66;

import java.util.Arrays;

/**
 * @Description 剪绳子
 * 给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * @auther wcc
 * @create 2019-09-07 20:17
 */
public class offer60 {

    public static void main(String[] args) {
        offer60 offer60 = new offer60();
        int i = offer60.cutRope(20);
        System.out.println(i);
    }

    int cutRope(int number) {
        int[] dp = new int[number];
        dp[0] = number-1;
        int max = dp[0];
        for(int i = 1; i < number; i++){
            if(number % i == 0){
                dp[i] = (int) Math.pow(i, number / i);
            }else {
                dp[i] = (int) Math.pow(i, number / i);
                dp[i] = dp[i] * (number % i);
            }
            if(dp[i] > max){
                max = dp[i];
            }
        }
        System.out.println(Arrays.toString(dp));
        return max;
    }

}
