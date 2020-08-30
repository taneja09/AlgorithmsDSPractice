package LC_Medium;
/**
 
 Time Complexity - O(max(m,n))
 Reason - traversal required till max length of linked list
 
 Space Complexity - O(max(m,n))
 Reason - New linked list size for result is max out of both + 1 (carry)
*/

import Helpers.ListNode;

public class M0002_AddTwoNumbers {
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(-1);
		ListNode head = res;
		int c = 0;
		while(l1 != null || l2 != null){
			int x = (l1 != null) ? l1.val : 0;
			int y = (l2 != null) ? l2.val : 0;
			int sum = c + x + y;
			c = sum / 10;
			res.next = new ListNode((sum)%10);
			res = res.next;
			
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
		}
		
		if(c > 0) res.next = new ListNode(c);
		return head.next;
	}
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(3);
		
		M0002_AddTwoNumbers cl = new M0002_AddTwoNumbers();
		ListNode res = cl.addTwoNumbers(l1,l2);
		ListNode.printLinkList(res);
		
	}
}
