package offer.offer41_66;


import offer.offer11_40.ListNode;

/**
 * 链表中环的入口结点
 * <p>
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * 思路：一个慢指针，一下走一步，一个快指针，一下走两步，如果能相遇，说明一定有环
 * 假设环路口距离头节点为a，相遇点距离环入口为c（顺时针），入口点距离相遇点为b（顺时针）
 * 那么就可以的出：(b+c)*k + a + b = 2*(a+b)
 * 化简：a = (k-1)(b+c)+c
 */
public class offer50 {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }

        //如果有环，求出相遇点
        ListNode low = pHead;
        ListNode fast = pHead;
        while (low.next != null && fast.next.next != null) {
            low = low.next;
            fast = fast.next.next;
            if (low == fast) {
                break;
            }
        }

        //如果一个条件为null，说明没有环
        if (low.next == null || fast.next.next == null) {
            return null;
        }

        //有环，求出入口，下次相遇一定在入口点
        low = pHead;
        while (low != fast){
            fast = fast.next;
            low = low.next;
        }

        return low;

    }


}
