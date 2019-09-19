package offer.offer41_66;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 机器人的运动范围
 *
 * 地上有一个m行和n列的方格。
 * 一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
 * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
 * 请问该机器人能够达到多少个格子？
 *
 * 深度优先遍历
 */
public class offer42 {


    public static void main(String[] args) {
        offer42 offer42 = new offer42();
        int i = offer42.movingCount(11, 10, 6);
        System.out.println(i);
    }

    //定义方向：下，右，上，左
    int[] x = {0, 1, 0, -1};
    int[] y = {1, 0, -1, 0};

    /**
     * @param threshold k
     * @param rows      m
     * @param cols      n
     * @return
     */
    public int movingCount(int threshold, int rows, int cols) {
        if(threshold < 0){
            return 0;
        }
        //地图 0可以走，1被走过，2是不能走
        int[][] map = new int[rows][cols];
        map[0][0] = 1;
        //计数器
        int[] count = new int[]{1};
        tryGo(map, rows, cols, 0, 0, count, threshold);
        return count[0];
    }

    /**
     * @param map   地图
     * @param rows  地图的行数
     * @param cols  地图的列数
     * @param xx    当前位置的x坐标
     * @param yy    当前位置的y坐标
     * @param count 计数器
     */
    public void tryGo(int[][] map, int rows, int cols, int xx, int yy, int[] count, int threshold) {
        //尝试4个方向
        for (int i = 0; i < 4; i++) {
            int xxx = xx + x[i];
            int yyy = yy + y[i];
            //检查
            if (check(map, xxx, yyy, rows, cols, threshold)) {
                count[0]++;
                map[xxx][yyy] = 1;
                //打印map
                printMap(map);
                tryGo(map, rows, cols, xxx, yyy, count, threshold);
            }
        }
    }

    public void printMap(int[][] map){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------>");
    }

    /**
     * 检查这个位置是否可行
     *
     * @param map
     * @param xxx
     * @param yyy
     * @param rows
     * @param cols
     * @return
     */
    public boolean check(int[][] map, int xxx, int yyy, int rows, int cols, int threshold) {

        if (xxx >= rows || yyy >= cols || xxx < 0 || yyy < 0) {
            return false;
        }

        if (map[xxx][yyy] == 1 || map[xxx][yyy] == 2) {
            return false;
        }

        //坐标的数位之和大于k
        int sum = sum(getArr(xxx))+sum(getArr(yyy));
        if(sum > threshold){
            map[xxx][yyy] = 2;
            return false;
        }

        return true;
    }

    public int sum(ArrayList<Integer> list){
        int sum = 0;
        for(int i : list){
            sum+=i;
        }
        return sum;
    }


    public ArrayList<Integer> getArr(int n) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (n != 0) {
            arrayList.add(n % 10);
            n /= 10;
        }
        return arrayList;
    }

}
