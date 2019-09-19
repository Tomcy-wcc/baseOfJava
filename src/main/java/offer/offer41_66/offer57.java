package offer.offer41_66;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Description 孩子们的游戏(圆圈中最后剩下的数)
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。
 * 其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
 * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
 * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
 * 可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 * 如果没有小朋友，请返回-1
 * @auther wcc
 * @create 2019-09-04 10:26
 */
public class offer57 {

    public static void main(String[] args) {
        offer57 offer57 = new offer57();
        int i = offer57.LastRemaining_Solution(7, 2);
        System.out.println(i);
    }

    public int LastRemaining_Solution(int n, int m) {
        if(n == 0 || m == 0){
            return -1;
        }
        int[] flags = new int[n];
        Arrays.fill(flags, 1);
        //记录圈里面人数
        int p = n;
        int i = 0;
        while (p != 1){
            //记录报数的进度
            int count = 0;
            while (true){
                count += flags[i%n];
                if(count == m){
                    break;
                }
                i++;
            }
            //出圈
            flags[i%n] = 0;
            p--;
            System.out.println(Arrays.toString(flags));
        }
        int j;
        for(j = 0; j < flags.length; j++){
            if(flags[j] != 0){
                break;
            }
        }
        return j;
    }


}
