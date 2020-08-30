package LC_Medium;

import Helpers.ListNode;
/**
 Time Complexity -  O(N)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
public class M0142_LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode slow = head,fast = head;
        while(slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)
                break;
        }
        fast = head;
        while(slow!= null && fast != null && slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
//        head.next.next = new LinkListNode(0);
//        head.next.next.next = new LinkListNode(-4);
//        head.next.next.next.next=  head.next;

        M0142_LinkedListCycleII cl = new M0142_LinkedListCycleII();
        ListNode res = cl.detectCycle(head);
//        System.out.println(res.val);
    }
}
