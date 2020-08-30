package LC_Easy;

import Helpers.ListNode;

public class E0141_LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) return false;
		ListNode slow = head;
		ListNode fast = head.next;
		
		while(slow != null && fast != null && slow.next!= null && fast.next != null){
			if(slow == fast) return true;
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return false;
	}
	public static void main(String[] args) {
		ListNode head  = new ListNode(7);
		head.next = new ListNode(13);
		head.next.next = new ListNode(11);
		head.next.next.next = new ListNode(10);
		head.next.next.next.next = head.next.next;
		
		
		E0141_LinkedListCycle cl = new E0141_LinkedListCycle();
		System.out.println(cl.hasCycle(head));
		
	}
}
