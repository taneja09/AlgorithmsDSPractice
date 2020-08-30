package LC_Easy;

import Helpers.ListNode;

public class E0206_ReverseLinkedList {
	/********** Recursive Solution TC - O(n) SC = O(1) **********************/
	public ListNode reverseList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode res = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return res;
	}
	
	/********** Iterative Solution TC - O(n) SC = O(1) **********************/
	public ListNode reverseListIterative(ListNode head) {
		ListNode prev = null, curr = head, next = null;
		
		while(curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		return prev;
	}
	
	public static void main(String[] args) {
		ListNode head  = new ListNode(7);
		head.next = new ListNode(13);
		head.next.next = new ListNode(11);
		head.next.next.next = new ListNode(10);
		head.next.next.next.next = null;
		E0206_ReverseLinkedList cl = new E0206_ReverseLinkedList();
		ListNode.printLinkList(head);
		//LinkListNode res = cl.reverseList(head);
		ListNode res1 = cl.reverseListIterative(head);
		//LinkListNode.printLinkList(res);
		ListNode.printLinkList(res1);
		
	}
}
