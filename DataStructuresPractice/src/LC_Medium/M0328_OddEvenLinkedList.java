package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - traversal required for complete linked list
 
 Space Complexity - O(1)
 Reason - Constant space
*/
import Helpers.ListNode;

public class M0328_OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        ListNode even = head, odd = head.next; // Pointers for traversal
        ListNode ptr1 = even, ptr2 = odd; // Pointers to join 2 list later and returning

        // Example 1->2->3->4->5
        while(even != null && odd != null && even.next != null && odd.next!= null){
            even.next =  odd.next;   //1-> 3
            even = even.next;       // move pointer to next even node 3 ...
            odd.next = even.next;  //2-> 4
            odd = odd.next;       // move pointer to next odd node 4 ...
        }
        even.next = ptr2;  //  will join even and odd lists
        return ptr1;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = null;

        M0328_OddEvenLinkedList cl = new M0328_OddEvenLinkedList();
        ListNode res = cl.oddEvenList(head);
        ListNode.printLinkList(res);
    }
}