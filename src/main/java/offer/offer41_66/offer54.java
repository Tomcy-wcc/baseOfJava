package offer.offer41_66;

/**
 * 左旋转字符串
 * <p>
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
 * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class offer54 {

    public static void main(String[] args) {
        offer54 offer54 = new offer54();
        String s = offer54.LeftRotateString("", 3);
        System.out.println(s);
    }

    public String LeftRotateString(String str, int n) {
        //排除特殊情况
        if ("".equals(str)) {
            return "";
        }

        if(str !=null  && n % str.length() == 0){
            return str;
        }

        int len = str.length();
        //求出实际向左移动的位数
        int k = n % len;
        char[] charArray = str.toCharArray();
        //依次交换前n个字符
        for (int i = 0, j = k - 1; i <= j; i++, j--) {
            swap(charArray, i, j);
        }

        //依此交换反转后n个字符
        for (int i = n, j = len - 1; i <= j; i++, j--) {
            swap(charArray, i, j);
        }

        //反转全部
        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            swap(charArray, i, j);
        }

        return new String(charArray);
    }

    public void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
