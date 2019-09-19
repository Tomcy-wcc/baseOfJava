package offer.offer11_40;

import java.util.Arrays;

/**
 * 调整数组顺序使奇数位于偶数前面
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class offer13 {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        reOrderArray(array);
    }

    public static void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {//假如这个数是偶数
                int j;
                //找到后面的第一个奇数
                for (j = i + 1; j < array.length; j++) {
                    if (array[j] % 2 == 1) {
                        break;
                    }
                }
                //则j为奇数的下标
                if (j == array.length) {//说明后面没有奇数了
                    break;
                }
                int temp = array[j];
                System.out.println(temp);
                for (int k = j; k > i; k--) {
                    array[k] = array[k - 1];
                }
                array[i] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
