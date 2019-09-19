package offer.offer41_66;

import offer.offer11_40.ListNode;

/**
 * 删除链表中重复的结点
 *
 * 在一个排序的链表中，存在重复的结点，
 * 请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 *        p c
 *          p  c
 *             p  c
 *             p  c
 *             p        c
 *
 *
 *  1,1,1,1,1,1,2
 *p c n
 *p c   n
 *p c     n
 *p c       n
 *p c         n
 *p             c
 */
public class offer49 {

    public ListNode deleteDuplication(ListNode pHead) {
        ListNode current = pHead;
        ListNode pre = new ListNode(0);
        pre.next = current;

        ListNode x = pre;

        boolean flag = false;
        while (current.next != null){
            if(current.val == current.next.val){
                current.next = current.next.next;//删去后面的重复结点
                flag = true;
            }else {
                if(flag){//删去第一个重复结点
                    current = current.next;
                    pre.next = current;
                    flag = false;
                }else{
                    pre = current;
                    current = current.next;
                    pre.next = current;
                }
            }
        }
        //特殊情况，current.next == null，退出循环,所以还要删去第一个重复结点
        if(flag){
            current = current.next;
            pre.next = current;
        }
        return x.next;
    }
}
