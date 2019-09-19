package offer.offer11_40;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 第一个只出现一次的字符
 *
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.
 */
public class offer33 {

    public static void main(String[] args) {
        offer33 offer33 = new offer33();
        int i = offer33.FirstNotRepeatingChar("aaabbbcccddd");
        System.out.println(i);
    }

    public int FirstNotRepeatingChar(String str) {
        HashMap<String, Integer> hashMap = new HashMap<>();//值与位置
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < str.length(); i++){
            if(!hashMap.containsKey(""+str.charAt(i))){
                hashMap.put(""+str.charAt(i), i);
                //保存位置
                list.add(i);
            }else {//如果存在key，说明第二次出现，从list中移除位置
                list.remove(hashMap.get(""+str.charAt(i)));
            }
        }
        if(list.isEmpty()){
            return -1;
        }
        return list.get(0);
    }
}
