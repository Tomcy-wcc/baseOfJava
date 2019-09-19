package offer.offer41_66;

import java.util.Arrays;

/**
 * @Description 构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * @auther wcc
 * @create 2019-09-03 10:04
 */
public class offer55 {
    public static void main(String[] args) {
        offer55 offer55 = new offer55();
        int[] multiply = offer55.multiply(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(multiply));
    }

    public int[] multiply(int[] A) {
        int len = A.length;
        //先依次算出A[0] A[0]*A[1] ... A[0]*A[1]*...*A[n-2]*A[n-1]
        int[] pre = new int[len];
        for(int i = 0; i < len; i++){
            pre[i] = i == 0 ? A[0] : A[i] * pre[i-1];
        }
        System.out.println(Arrays.toString(pre));

        //再依此算出A[0]*A[1]*...*A[n-2]*A[n-1] ... A[0]*A[1] A[0]
        int[] post = new int[len];
        for(int i = len-1; i >= 0; i--){
            post[i] = i == len-1 ? A[len-1] : A[i] * post[i+1];
        }
        System.out.println(Arrays.toString(post));
        //构建数组B
        int[] B = new int[len];
        for(int i = 0; i < len; i++){
            if(i == 0) {
                B[i] = post[i+1];
            }else if(i == len-1){
                B[i] = pre[i-1];
            }else{
                B[i] = pre[i-1]*post[i+1];
            }
        }
        return B;
    }

}
