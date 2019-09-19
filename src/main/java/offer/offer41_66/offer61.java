package offer.offer41_66;

/**
 * @Description 把字符串转换成整数
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 * @auther wcc
 * @create 2019-09-07 20:47
 */
public class offer61 {

    public static void main(String[] args) {
        offer61 offer61 = new offer61();
        int i = offer61.StrToInt("-1a56");
        System.out.println(i);
    }

    public int StrToInt(String str) {
        if(str.equals("")){
            return 0;
        }
        int res = 0;
        int _index = 0, flag = 1;
        if(str.charAt(_index) =='+' || str.charAt(_index) =='-'){
            if(str.charAt(_index) =='-'){
                flag = 0;
            }
            _index++;
        }
        if(str.length() == _index){
            return 0;
        }
        while (_index < str.length()){
            if(str.charAt(_index) >= '0' && str.charAt(_index) <='9'){
                res = res*10 + (str.charAt(_index) - '0');
                _index++;
            }else {
                return 0;
            }
        }
        return flag == 1 ? res : -res;
    }
}
