package LC_Medium;
import Helpers.ListNode;

public class M0148_SortList {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)return head;

        // step 1. cut the list to two halves
        ListNode slow = head;
        ListNode fast = head.next;

        while(slow != null && fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = slow.next;
        slow.next = null;
        slow = head;
    
        // step 2. sort each half
        ListNode l1 = sortList(slow);
        ListNode l2 = sortList(fast);
        
        // step 3. merge l1 and l2
        return merge(l1,l2);

    }

    public ListNode merge(ListNode left, ListNode right){
       // System.out.println(left.val + " " + right.val);
        ListNode head = new ListNode(0);
        ListNode curr = head;

        while(left != null && right != null){
            if(left.val <= right.val){
                curr.next = left;
                left = left.next;
            }else{
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        if(left != null) curr.next = left;
        else if(right != null) curr.next = right;

        return head.next;
    }

    public static void main(String[] args) {
        ListNode ll = new ListNode(4);
        ll.next = new ListNode(2);
        ll.next.next  = new ListNode(1);
        ll.next.next.next  = new ListNode(3);
        M0148_SortList cl = new M0148_SortList();
        ListNode node =  cl.sortList(ll);

        ListNode.printLinkList(node);
    }
}
