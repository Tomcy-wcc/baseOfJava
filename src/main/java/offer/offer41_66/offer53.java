package offer.offer41_66;

import java.util.Arrays;

/**
 * 翻转单词顺序列
 *
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
 * 同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
 * 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
 * 正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 */
public class offer53 {

    public static void main(String[] args) {
        offer53 offer53 = new offer53();
        String s = offer53.ReverseSentence("student. a am I");
        System.out.println(s);
    }

    public String ReverseSentence(String str) {

        if (str==null || str.trim().equals("")){
            return str;
        }

        //先分割单词
        String[] split = str.split(" ");
        for(int i = 0; i < split.length/2; i++){
            String temp = split[i];
            split[i] = split[split.length-i-1];
            split[split.length-i-1] = temp;
        }

        System.out.println(Arrays.toString(split));

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < split.length; i++){
            stringBuilder.append(split[i]);
            if(i != split.length-1){
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();

    }

}
