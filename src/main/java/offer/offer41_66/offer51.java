package offer.offer41_66;

import java.util.LinkedList;

/**
 * 字符流中第一个不重复的字符
 * <p>
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * <p>
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class offer51 {

    public static void main(String[] args) {
        offer51 offer51 = new offer51();
        String str = "google";
        for(int i = 0; i < str.length(); i++){
            offer51.Insert(str.charAt(i));
            char c = offer51.FirstAppearingOnce();
            System.out.println(c);
        }
    }

    LinkedList<Character> queue = new LinkedList<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        int index = queue.indexOf(ch);
        if(index != -1){
            queue.remove(queue.get(index));
        }else {
            queue.offer(ch);
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        if (queue.isEmpty()) {
            return '#';
        } else {
            return queue.getFirst();
        }
    }
}
