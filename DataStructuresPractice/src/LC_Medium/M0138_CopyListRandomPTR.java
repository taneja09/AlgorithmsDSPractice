package LC_Medium;
/**
 Time Complexity -  O(N)
 Reason - Linear scan
 
 Space Complexity - O(1)
 Reason -  No extra space usage
 */
import Helpers.ListNode;

public class M0138_CopyListRandomPTR {

    public ListNode copyRandomList(ListNode head) {

        if(head == null) return null;
        ListNode current = head, temp = null;

        //insert temporary node between all nodes  A-> A' -> B -> B' -> C -> C'  ......
        while(current != null){
            temp = current.next;
            current.next = new ListNode(current.val);
            current.next.next = temp;

            current = temp;
        }

        //set back head to current node
        current = head;

        // adjust the random pointers of the
        // newly added nodes
        while(current != null){
            if(current.next != null){
                // If A.random points to B then = New List A-> A' -> B -> B'
                // A'.random (current.next.random) will be assigned with B' (current.random.next)
                current.next.random = (current.random != null) ? current.random.next : null;
            }

            // move to the next newly added node by
            // skipping an original node
            current = (current.next != null) ? current.next.next : null;
        }

        //set back  heads original and clone nodes

        ListNode org = head; ListNode clone = head.next;
        temp = clone; // save it temporarily

        // now separate the original list and copied list

        while(org != null && clone != null){
            org.next = (org.next != null) ? org.next.next : null;
            clone.next = (clone.next != null) ? clone.next.next : null;
            org = org.next;   // next node
            clone = clone.next;
        }

        return temp;

    }


    public static void main(String[] args) {
        ListNode head  = new ListNode(7);

        head.next = new ListNode(13);
        head.random = null;

        head.next.random = head;
        head.next.next = new ListNode(11);

        head.next.next.next = new ListNode(10);
        head.next.next.random = head.next.next.next.next;

        head.next.next.next.next = new ListNode(1);
        head.next.next.next.random = head.next.next;

        head.next.next.next.next.next = null;
        head.next.next.next.next.random = head;

        M0138_CopyListRandomPTR cl = new M0138_CopyListRandomPTR();
        ListNode res =  cl.copyRandomList(head);
        ListNode.printLinkList(res);
    }
}
