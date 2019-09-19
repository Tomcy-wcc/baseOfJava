package offer.offer11_40;


import java.util.ArrayList;

/**
 * 丑数
 * <p>
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class offer34 {

    public static void main(String[] args) {
        offer34 offer34 = new offer34();
        for(int i = 1; i <=100; i++){
            int j = offer34.GetUglyNumber_Solution1(i);
            int k = offer34.GetUglyNumber_Solution2(i);
            System.out.println("j = "+j+"--->" +"k = "+ k+"-->"+"j == k"+(j==k));
        }
    }


    public int GetUglyNumber_Solution2(int index){
        if(index < 7){
            return index;
        }
        int p2 = 0, p3 = 0, p5 = 0, newNum = 1;
        ArrayList<Integer> result = new ArrayList<>();
        result.add(newNum);
        while (result.size() <= index){
            //找出最小的一个数，每次都以最小的为基准
            newNum = min(result.get(p2)*2, result.get(p3)*3, result.get(p5)*5);
            if(result.get(p2)*2 == newNum) p2++;
            if(result.get(p3)*3 == newNum) p3++;
            if(result.get(p5)*5 == newNum) p5++;
            result.add(newNum);
        }
        return result.get(index-1);
    }

    public int min(int i, int j, int k){
        int[] arr = {i, j, k};
        int min = arr[0];
        for(int p = 1; p < arr.length; p++){
            if(arr[p] < min){
                min = arr[p];
            }
        }
        return min;
    }


    public int GetUglyNumber_Solution1(int index) {
        if(index == 1){
            return 1;
        }
        int num = 2;
        while(true){
            if(isUglyNumber(num)){
                --index;
                if(index == 1){
                    break;
                }
            }
            num++;
        }
        return num;
    }

    public boolean isUglyNumber(int n){
        while (n % 5 == 0) {
            n /= 5;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        if(n != 1){
            return false;
        }
        return true;
    }
}
