package LC_Medium;


/**
 
 Time Complexity - O(n)
 Reason - One pass
 
 Space Complexity - O(1)
 Reason - No extra space
*/
import Helpers.ListNode;

public class M0019_RemoveNthNodeFromLast {
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		
		if (head == null) return head;
		ListNode temp = new ListNode(-1);
		temp.next = head;
		ListNode first = temp, second = temp;
		
		int count = 0;
		
		while (first != null && second != null && first.next != null && second.next != null) {
			while (count < n) {
				first = first.next;
				count++;
			}
			
			if(first.next!= null) {
				first = first.next;
				second = second.next;
			}
		}
		
		if(head == second.next) return head.next;
		
		second.next = second.next.next;
		return head;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
  		head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next=  new ListNode(5);
		head.next.next.next.next.next = null;
		
		M0019_RemoveNthNodeFromLast cl = new M0019_RemoveNthNodeFromLast();
		ListNode result = cl.removeNthFromEnd(head,5);
		ListNode.printLinkList(result);
		
	}
}
