package offer.offer11_40;

/**
 * 数值的整数次方
 *
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class offer12 {
    public static void main(String[] args) {
        System.out.println(Power(5, 5));


        System.out.println(Math.pow(5, 5));

    }

    public static double Power(double base, int exponent) {

        //处理次方为0或者数值为0的情况
        if(base == 0){
            if(exponent == 0){
                return 1;
            }
            return 0;
        }
        double res = 1.0;
        //次方为负数
        if(exponent < 0){
            for(int i = 1; i <= -exponent; i++){
                res *= (1.0 / base);
            }
        }else{//次方为正数
            for(int i = 1; i <= exponent; i++){
                res *= base;
            }
        }

        return res;

    }
}
