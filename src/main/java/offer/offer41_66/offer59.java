package offer.offer41_66;

/**
 * @Description 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 * @auther wcc
 * @create 2019-09-07 20:03
 */
public class offer59 {

    int Add(int num1, int num2){
        while (num2 != 0){
            int temp = num1 ^ num2; // 计算和，没有进位
            num2 = (num1 & num2) << 2; //计算进位
            num1 = temp;
        }
        return num1;
    }

}
