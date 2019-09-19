package offer.offer11_40;

/**
 * 合并两个排序的链表
 *
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class offer16 {

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode list3 = null;
        if (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                list3 = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                list3 = new ListNode(list2.val);
                list2 = list2.next;
            }
        }
        ListNode p = list3;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        while (list1 != null) {
            p.next = list1;
            list1 = list1.next;
            p = p.next;
        }
        while (list2 != null) {
            p.next = list2;
            list2 = list2.next;
            p = p.next;
        }
        return list3;
    }

}
