package offer.offer41_66;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 数据流中的中位数
 *
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * 我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 */
public class offer46 {

    private ArrayList<Integer> list = new ArrayList<>();

    public void Insert(Integer num) {
        list.add(num);
    }

    public Double GetMedian() {
        Collections.sort(list);
        if((list.size() & 1) == 1){
            return Double.valueOf(list.get(list.size()/2));
        }else{
            return (Double.valueOf(list.get(list.size()/2))+Double.valueOf(list.get((list.size()-1)/2)))/2;
        }
    }
}
