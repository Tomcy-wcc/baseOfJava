package offer.offer1_10;

/**
 * 替换空格
 *
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class offer2 {

    public String replaceSpace(StringBuffer str) {
        String s = str.toString();
        int _index = s.indexOf(" ");
        while (_index != -1 && _index < s.length()) {
            s = s.replace(" ", "%20");
            _index = s.indexOf(" ", _index+1);
        }
        return s;
    }
}
