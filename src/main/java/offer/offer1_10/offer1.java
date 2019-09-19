package offer.offer1_10;

/**
 * 二维数组中的查找
 *
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class offer1 {
    public boolean Find(int target, int [][] array) {
        if(array.length == 0 || array[0].length == 0) {
            return false;
        }
        int col = array.length-1;//代表向下
        int row = 0;//代表往左
        while(row <= array.length-1 && col >= 0){
            if(array[row][col] == target){
                return true;
            }else if(array[row][col] < target){
                row += 1;
            }else{
                col -= 1;
            }
        }
        return false;
    }
}
