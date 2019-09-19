package offer.offer41_66;

/**
 * 矩阵中的路径
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如:
 * a b c e
 * s f c s
 * a d e e
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 *
 * 深度优先遍历
 */
public class offer43 {

    public static void main(String[] args) {
        offer43 offer43 = new offer43();
        char[] matrix = "abcesfcsadee".toCharArray();
        char[] str = "bcced".toCharArray();
        boolean b = offer43.hasPath(matrix, 3, 4, str);
        System.out.println(b);
    }


    //定义方向：下，右，上，左
    int[] x = {0, 1, 0, -1};
    int[] y = {1, 0, -1, 0};

    /**
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        //将matrix转成矩阵
        char[][] data = new char[rows][cols];
        for(int i = 0; i < matrix.length; i++){
            data[i/cols][i%cols] = matrix[i];
        }
        printMap(data);
        //遍历矩阵
        for(int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                if(data[i][j] == str[0]){
                    if(str.length == 1){
                        return true;
                    }
                    System.out.println("i = "+i+"--->"+"j = "+j);
                    //结果
                    int[] result = new int[1];
                    int[][] map = new int[rows][cols];
                    map[i][j] = 1;
                    printMap(map);
                    tryGo(map, data, rows, cols, str, i, j, result, 1);
                    map[i][j] = 0;
                    if(result[0] == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void tryGo(int[][] map, char[][] data, int rows, int cols, char[] str, int xx, int yy, int[] result, int deep) {

        for(int i = 0; i < 4; i++){
            int xxx = xx + x[i];
            int yyy = yy + y[i];
            if(check(map, data, rows, cols, xxx, yyy, deep, str)){
                map[xxx][yyy] = 1;
                System.out.println("i = "+xxx+"--->j = "+yyy);
                if(deep == str.length-1){
                    result[0] = 1;
                    break;
                }
                printMap(map);
                tryGo(map, data, rows, cols, str, xxx, yyy, result, deep+1);
                map[xxx][yyy] = 0;
            }
        }
    }

    private boolean check(int[][] map, char[][] data, int rows, int cols, int xxx, int yyy, int deep, char[] str) {
        if(xxx >= rows || yyy >= cols || xxx < 0 || yyy < 0){
            return false;
        }

        if (map[xxx][yyy] == 1 || map[xxx][yyy] == 2) {
            return false;
        }

        System.out.println(xxx+" "+yyy);
        if(data[xxx][yyy] != str[deep]){
            map[xxx][yyy] = 2;
            return false;
        }

        return true;
    }


    private void printMap(char[][] map){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------->");
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
}
