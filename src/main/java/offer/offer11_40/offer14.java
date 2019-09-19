package offer.offer11_40;

import java.util.ArrayList;

/**
 * 链表中倒数第k个结点
 *
 * 输入一个链表，输出该链表中倒数第k个结点。
 */
public class offer14 {

    public ListNode FindKthToTail(ListNode head,int k) {
        ArrayList<ListNode> listNodes = new ArrayList<>();
        int sum = 0;
        ListNode p = head;
        //遍历整个链表
        while(p!=null){
            listNodes.add(p);
            p = p.next;
        }
        if(k > listNodes.size() || k==0){
            return null;
        }
        //取出第listNodes.size()-k位置上的值
        return listNodes.get(listNodes.size()-k);
    }
}


