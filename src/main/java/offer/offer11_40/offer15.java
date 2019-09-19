package offer.offer11_40;

/**
 * 反转链表
 *
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class offer15 {

    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        ListNode p = head;
        while (p!=null){
            ListNode temp = new ListNode(p.val);
            temp.next = pre;
            pre = temp;
            p = p.next;
        }
        return pre;
    }
}
