package offer.offer11_40;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 顺时针打印矩阵
 * <p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵：
 * 1  2  3  4
 * 5  6  7  8
 * 9  10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10
 */
public class offer19 {

    public static void main(String[] args) {
        int n =3;
        int m = 5;
        int[][] matrix = new int[n][m];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = count++;
            }
            System.out.println(Arrays.toString(matrix[i]));
        }
        printMatrix(matrix);
    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> integers = new ArrayList<>();
        int row = matrix.length;
        int col = row > 0 ? matrix[0].length : 0;
        int min = row < col ? row : col;
        int circle = min / 2;
        //System.out.println(circle);
        for (int i = 0; i < circle; i++) {
            //上
            for (int j = i; j < col - i - 1; j++) {
                integers.add(matrix[i][j]);
            }
            //右
            for (int j = i; j < row - i - 1; j++) {
                integers.add(matrix[j][col - i - 1]);
            }
            //下
            for (int j = col - i - 1; j > i; j--) {
                integers.add(matrix[row - i - 1][j]);
            }
            //左
            for (int j = row - i - 1; j > i; j--) {
                integers.add(matrix[j][i]);
            }

        }
        //处理特殊情况：
        /**
         * [1, 2, 3, 4, 5]
         * [6, 7, 8, 9, 10]
         * [11, 12, 13, 14, 15]
         * 当n！=m时，要单独处理最中间的一行或一列
         */
        if (min % 2 != 0) {
            //System.out.println(min);
            if(min == col){
                for(int i = circle; i < row - circle; i++){
                    integers.add(matrix[i][circle]);
                }
            }else{
                for(int i = circle; i < col - circle; i++){
                    integers.add(matrix[circle][i]);
                }
            }
        }
        //System.out.println(integers);

        return integers;

    }
}
