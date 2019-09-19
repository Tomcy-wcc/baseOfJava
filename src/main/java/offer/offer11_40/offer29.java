package offer.offer11_40;

/**
 * 整数中1出现的次数（从1到n整数中1出现的次数）
 *
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 *
 *
 * 总结：10*base+pow(10, i-1)
 */

public class offer29 {


    public static void main(String[] args) {

        offer29 offer29 = new offer29();

        boolean flag = true;
        for(int j = 1; j <= 999999; j++){
            int sum = 0;
            for(int i = 1; i <= j; i++){
                sum+=offer29.count(i);
            }
            //System.out.println(sum);

            int i = offer29.NumberOf1Between1AndN_Solution(j);
            System.out.println(j+"---->"+i+"--->"+sum+"---->"+(i==sum));
            if(i != sum) {
                flag = false;
                return;
            }
        }
        System.out.println(flag);


    }

    //计算一个数有多少个1
    public int count(int n){
        int count = 0;
        int m = n;
        while (n != 0){
            if(n % 10 == 1){
                count+=1;
            }
            n /= 10;
        }
        return count;
    }

    /**
     * 返回一个表
     * 1位数   1
     * 2位数   19
     * 3位数   280
     * @param n 位数
     * @return
     */
    public int[] map(int n){
        int[] map = new int[n+1];
        map[0] = 0;
        map[1] = 1;
        for(int i = 2; i < map.length; i++){
            int x = 0;
            for(int j = i-1; j >= 0; j--){
                x+=map[j];
            }
            map[i] = (int) (9*x+Math.pow(10, i-1));
        }
        return map;
    }

    /**
     * 将n用数组表示
     * @param n
     * @return
     */
    public int[] getArr(int n){
        int[] arr = new int[10];
        int count = 0;
        int p = 1;
        while (n != 0){
            arr[p++] = n % 10;
            n /= 10;
            count++;
        }
        arr[0] = count;
        return arr;
    }

    /**
     * 计算总数
     * 4位 = 4000
     * 3位 = 300
     * 2位 = 20
     * 1位 = 1
     * @param map
     * @param end
     * @return
     */
    public int sum(int[] map, int end){
        int sum = 0;
        for(int i = end; i > 0; i--){
            int x = 0;
            for(int j = i-1; j >= 0; j--){
                x+=map[j];
            }
            sum+=9*x+Math.pow(10, i-1);
        }
        System.out.println("sum = "+sum);
        return sum;
    }

    public int NumberOf1Between1AndN_Solution(int n) {
        if(n == 0){
            return 0;
        }
        int[] arr = getArr(n);
        int[] map = map(arr[0]);
        //System.out.println(Arrays.toString(arr));
        //System.out.println(Arrays.toString(map));
        //获取数字长度
        int len = arr[0];
        //遍历数组
        int sum = 0;
        for(int i = len; i > 0; i--){
            if(arr[i] > 1){
                sum=sum+arr[i]*sum(map, i-1)+(int)Math.pow(10, i-1);
            }else if(arr[i] == 1){
                int count = 0;
                for(int j = i-1; j > 0; j--){
                    count+=arr[j]*(int)Math.pow(10, j-1);
                }
                sum=sum+arr[i]*sum(map, i-1)+count+1;
            }
        }
        return sum;
    }
}
