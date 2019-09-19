package offer.offer1_10;

/**
 * 矩形覆盖
 *
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class offer10 {
    public int RectCover(int n) {
        if(n == 0){
            return 0;
        }else if(n == 1) {
            return 1;
        }else if(n == 2){
            return 2;
        }else{
            return RectCover(n-1)+RectCover(n-2);
        }
    }
}
