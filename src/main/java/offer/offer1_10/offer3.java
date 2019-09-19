package offer.offer1_10;

import offer.offer11_40.ListNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 从尾到头打印链表
 *
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 */
public class offer3 {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode p = listNode;
        while(p!=null){
            list.add(p.val);
            p = p.next;
        }
        Collections.reverse(list);
        return list;
    }
}
