package offer.offer11_40;

/**
 * 二进制中1的个数
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class offer11 {

    public static void main(String[] args) {
        System.out.println(NumberOf1(-127));
    }


    public static int NumberOf1(int n) {
        /*//正数的原码
        ArrayList<Integer> b = new ArrayList<>();
        int t = Math.abs(n);
        while (t != 0){
            b.add(n % 2 == 0 ? 0 : 1);
            t >>= 1;
        }
        //增加符号位
        if (n >= 0) {
            b.add(0);
        } else {
            int size = b.size();
            //求反码
            for(int i = 0; i < size; i++){
                if(b.get(i) == 0){
                    b.set(i, 1);
                }else{
                    b.set(i, 0);
                }
            }
            //加1
            for(int i = 0; i < size; i++){
                if(b.get(i) == 0){
                    b.set(i, 1);
                    break;
                }else{
                    b.set(i, 0);
                }
            }
            b.add(1);
        }
        int count = 0;
        for(Integer i : b){
            if(i == 1){
                count++;
            }
        }
        System.out.println(b);
        return count;*/
        char[] chs = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        for(int i = 0; i < chs.length; i ++){
            if(chs[i] == '1')
                count++;
        }
        return count;
    }

}
