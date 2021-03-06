package offer.offer1_10;

/**
 * 跳台阶
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class offer8 {

    public int JumpFloor(int n) {
        if(n==0 || n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }else {
            return JumpFloor(n-1)+JumpFloor(n-2);
        }

    }
}
