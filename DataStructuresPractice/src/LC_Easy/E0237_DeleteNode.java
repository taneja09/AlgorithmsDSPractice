package LC_Easy;

import Helpers.ListNode;

public class E0237_DeleteNode {
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
	public static void main(String[] args) {
		
		ListNode head = new ListNode(4);
		head.next = new ListNode(5);
		head.next.next = new ListNode(1);
		head.next.next.next = new ListNode(9);
		head.next.next.next.next = null;
		E0237_DeleteNode cl = new E0237_DeleteNode();
		cl.deleteNode(head.next);
		ListNode.printLinkList(head);
	}
}
