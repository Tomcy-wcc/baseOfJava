package offer.offer11_40;

/**
 * 数字在排序数组中出现的次数
 * <p>
 * 统计一个数字在排序数组中出现的次数。
 */
public class offer32 {
    public static void main(String[] args) {
        int[] array = {3};
        offer32 offer32 = new offer32();
        int i = offer32.GetNumberOfK(array, 3);
        System.out.println(i);
    }

    public int GetNumberOfK(int[] array, int k) {
        if(array.length == 1){
            if(array[0] == k){
                return 1;
            }
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (k > array[mid]) {
                left = mid + 1;
            } else if (k < array[mid]) {
                right = mid - 1;
            } else {
                int count = 1;
                for(int i = mid-1; i >=left; i--){
                    if(array[i] == k){
                        count++;
                    }else{
                        break;
                    }
                }
                for(int i = mid+1; i <= right; i++){
                    if(array[i] == k){
                        count++;
                    }else{
                        break;
                    }
                }
                return count;
            }
        }
        return 0;
    }

}
