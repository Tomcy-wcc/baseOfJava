package offer.offer41_66;

import java.util.ArrayList;
import java.util.List;

/**
 * 表示数值的字符串
 *
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 */
public class offer52 {

    public boolean isNumeric(char[] str) {
        if(str == null){
            return false;
        }
        int index = 0;
        int len = str.length;
        //如果第一个字符是'-'或'+'
        if(str[0] == '-' || str[0] == '+'){
            index++;
        }
        if(index == len){
            return false;
        }

        return true;

    }
}
